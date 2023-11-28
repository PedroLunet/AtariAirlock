import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.*;

public class Arena {
    private int width;
    private int height;
    public Hero hero;
    private int score = 0;
    private List<Wall> walls;
    private List<Floor> floors;
    private List<Coin> coins;
    private List<Integer> monstersplevel = Arrays.asList(1, 2, 3, 3);
    private List<Monster> monsters = createMonsters();
    protected List<Key> keys = createKeys();
    private int elevatorKeysC = 0;
    private int wallKeysC = 0;
    private boolean lastKeyType;
    private List<Elevator> elevators = createElevators();
    private Elevator lastElevator = elevators.get(0);
    private int bottomD = 3;
    private int sideD = 15;
    private int floorSep = 4;
    private int elevatorW = 8;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(25, 24);
        walls = createWalls();
        floors = createFloors();
        coins = createCoins();
    }

    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowLeft) {
            hero.moveLeft(this);
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            hero.moveRight(this);
        } else if (key.getKeyType() == KeyType.ArrowUp) {
            hero.startJump();
        }
    }

    public List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        int lowestFloorY = height - bottomD;
        int highestFloorY = lowestFloorY - (floorSep * 3);

        for (int y = highestFloorY - 4; y <= lowestFloorY; y++) {
            walls.add(new Wall(sideD, y));
        }

        int mirroredX = width - sideD - 1; // Calculate mirrored X position

        for (int y = highestFloorY - 4; y <= lowestFloorY; y++) {
            walls.add(new Wall(mirroredX, y));
        }

        // Third wall (parallel to the left wall but inside by elevatorW)
        int parallelX = sideD + elevatorW; // Calculate X position
        for (int y = highestFloorY - 4; y <= lowestFloorY; y++) {
            walls.add(new Wall(parallelX, y));
        }

        // Fourth wall (mirrored position of the third wall)
        int mirroredX2 = width - parallelX - 1; // Calculate mirrored X position
        for (int y = highestFloorY - 4; y <= lowestFloorY; y++) {
            walls.add(new Wall(mirroredX2, y));
        }

        return walls;
    }

    public List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();
        Random random = new Random();
        int numCoins1 = random.nextInt(5) + 1;
        int numCoins2 = random.nextInt(5) + 1;
        int numCoins3 = random.nextInt(5) + 1;
        int numCoins4 = random.nextInt(5) + 1;
        int maxX = 75;
        int minX = 26;

        for (int i = 0; i < numCoins1; i++) {
            int x = random.nextInt((maxX - minX) + 1) + minX;
            coins.add(new Coin(x, 22));
        }
        for (int i = 0; i < numCoins2; i++) {
            int x = random.nextInt((maxX - minX) + 1) + minX;
            coins.add(new Coin(x, 18));
        }
        for (int i = 0; i < numCoins3; i++) {
            int x = random.nextInt((maxX - minX) + 1) + minX;
            coins.add(new Coin(x, 14));
        }
        for (int i = 0; i < numCoins4; i++) {
            int x = random.nextInt((maxX - minX) + 1) + minX;
            coins.add(new Coin(x, 10));
        }
        return coins;
    }


    public List<Floor> createFloors() {
        List<Floor> floors = new ArrayList<>();

        int bottomFloorY = height - bottomD;
        for (int x = sideD; x < width - sideD; x++) {
            floors.add(new Floor(x, bottomFloorY));
        }

        int middleFloorY = bottomFloorY - floorSep;
        int floorLength = width - 2 * (elevatorW + sideD);

        for (int i = 0; i < 4; i++) {
            int floorX = elevatorW + sideD;
            floors.addAll(createSingleFloor(floorX, middleFloorY, floorLength));
            middleFloorY -= floorSep;
        }
        return floors;
    }

    private List<Floor> createSingleFloor(int x, int y, int length) {
        List<Floor> singleFloor = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            singleFloor.add(new Floor(x + i, y));
        }
        return singleFloor;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#13022D")); //Dark Purple
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#9D0EB1")); //Light Purple
        graphics.putString(new TerminalPosition(70, 5), "Time Left= " + Game.getTime());
        graphics.putString(new TerminalPosition(20, 5), "SCORE " + score);
        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        for (Floor floor : floors) {
            floor.draw(graphics);
        }
        for (Monster monster : monsters) {
            monster.draw(graphics);
        }
        hero.draw(graphics);
        for (Key key : keys) {
            key.draw(graphics);
        }
        for (Elevator elevator : elevators) {
            elevator.draw(graphics);
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
    }



    public void removeCoin() {
        Iterator<Coin> coinIterator = coins.iterator();
        while (coinIterator.hasNext()) {
            Coin coin = coinIterator.next();
            if (hero.getPosition().samePosition(coin.getPosition())) {
                coinIterator.remove();
            }
        }
    }


    public boolean checkCoinCollision(Position heroPosition) {
        for (Coin coin : coins) {
            Position coinPosition = coin.getPosition();
            if (heroPosition.samePosition(coinPosition)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWalls(Position p){
        for (Wall wall : walls) {
            if(p.samePosition(wall.getPosition())){
                return true;
            }
        }
        return false; // No collision
    }
    public boolean checkMonsterCollision(Position heroPosition) {
        for (Monster monster : monsters) {
            Position monsterPosition = monster.getPosition();
            if (heroPosition.samePosition(monsterPosition)) {
                return true; // Se tiver Colisão
            }
        }
        return false; // Se nao tiver colisão
    }
    public boolean checkKeyCollision(Hero hero) {
        Position heroP = hero.getPosition();
        for (Key key : keys) {
            Position keyP = key.getPosition();
            if (heroP.samePosition(keyP)) {
                lastKeyType = key.getType();
                collectKeys();
                keys.remove(key);
                return true;
            }
        }
        return false;
    }


    public List<Monster> createMonsters () {
        int min = 24; // Parede da esquerda
        int max = 75; // Parede da direita
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(65, 24));
        for (int j = 1; j < monstersplevel.size(); j++) {
            for (int i = 0; i < monstersplevel.get(j); i++) {
                int ri = random.nextInt(max - min + 1) + min;
                monsters.add(new Monster(ri, 24 - 4 * j));
            }
        }
        return monsters;
    }
    public void moveMonsters () {
        for (Monster monster : monsters) {
            monster.move(this);
        }
    }
    public List<Key> createKeys () {
        //ESTA FUNCAO ESTA FEITA PARA A ARENA ANTIGA , ALTERAR LINHAS 118 119 122 E 126(Y COORDINATE)
        int min = 24; //Parede esquerda
        int max = 75; //Parede direita
        Random random = new Random();
        ArrayList<Key> keys = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 2; i++) {
                int ri = random.nextInt(max - min + 1) + min;
                boolean type = i == 0;
                keys.add(new Key(ri, 23 - 4 * j - 1, type));
            }
        }
        return keys;
    }
    public List<Elevator> createElevators () { //TODO
        ArrayList<Elevator> elevators = new ArrayList<>();
        elevators.add((new Elevator(new Position(37, 25), new Position(44, 25))));
        elevators.add((new Elevator(new Position(47, 21), new Position(54, 21))));
        elevators.add((new Elevator(new Position(57, 17), new Position(64, 17))));
        elevators.add((new Elevator(new Position(67, 13), new Position(74, 13))));
        return elevators;
    }
    public boolean isOnElevator (Hero hero){
        for (Elevator e : elevators) {
            ArrayList<Integer> temp = e.getPosition();
            int heroX = hero.getPosition().getX();
            int heroY = hero.getPosition().getY();
            if (heroX >= temp.get(0) && heroX <= temp.get(1) && heroY == temp.get(2) - 1) {
                System.out.println("Im on the elevator" + e);
                return true;
            }
        }
        return false;
    }
    public void collectKeys() {
        if (!lastKeyType) {
            elevators.get(elevatorKeysC).activateElevator();
            lastElevator = elevators.get(elevatorKeysC);
            elevatorKeysC++;
        }
        //else...
    }
    public void startElevator() {
        if (hero.isReady() && lastElevator.getActiveStatus()) {
            lastElevator.runElevator(hero);
            score++;
        }
    }
}