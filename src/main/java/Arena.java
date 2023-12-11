import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.*;

public class Arena {
    private int width;
    private int height;
    public Hero hero;
    protected int score = 0;
    private int level = 0;
    private List<NormalWall> walls;
    private List<Door> doors;
    private List<Wall> allWalls = new ArrayList<>();
    private List<Floor> floors;
    protected List<Coin> coins;
    private List<Integer> normalMonsterspLevel = Arrays.asList(2, 2, 2, 0);
    private List<Integer> shootingMonsterspLevel = Arrays.asList(1, 1, 1, 2);
    private List<Integer> speedMonsterspLevel =  Arrays.asList(0, 0, 1, 2);
    private List<Integer> yLevels = Arrays.asList(24, 20, 16, 12);
    private List<Monster> monsters = createMonsters();
    protected List<Key> keys = createKeys();
    private int elevatorKeysCount = 0;
    private List<Elevator> elevators = createElevators();
    private Elevator lastElevator = elevators.get(0);
    private int bottomD = 3;
    private int sideD = 15;
    private int floorSep = 4;
    private int elevatorW = 8;
    public List<Bullet> bullets = new ArrayList<>();
    private int levelsFlooded = -1;
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(78, 24);
        walls = createWalls();
        floors = createFloors();
        coins = createCoins(keys);
        doors = createDoors();
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

    public List<NormalWall> createWalls() {
        List<NormalWall> walls = new ArrayList<>();

        int lowestFloorY = height - bottomD;
        int highestFloorY = lowestFloorY - (floorSep * 3);

        for (int y = highestFloorY - 4; y <= lowestFloorY; y++) {
            walls.add(new NormalWall(sideD, y));
        }

        int mirroredX = width - sideD - 1; // Calculate mirrored X position

        for (int y = highestFloorY - 4; y <= lowestFloorY; y++) {
            walls.add(new NormalWall(mirroredX, y));
        }
        allWalls.addAll(walls);
        return walls;
    }

    public List<Door> createDoors() {
        List<Door> doors = new ArrayList<>();
        int signal = 1;
        //doors.add(new Door(76,24));
        for (int j : yLevels) {
            if (signal == 1) {
                doors.add(new Door(76, j));
                doors.add(new Door(23, j));
            }
            if (signal == -1) {
                doors.add(new Door(23, j));
                doors.add(new Door(76, j));
            }
            signal *= -1;
        }
        allWalls.addAll(doors);
        return doors;
    }

    public List<Key> createKeys() {
        int min = 24;
        int max = 75;
        Random random = new Random();
        ArrayList<Key> keys = new ArrayList<>();
        for (int i = 22; i > 11; i = i - 4) {
            int r1 = random.nextInt(max - min + 1) + min;
            int r2 = random.nextInt(max - min + 1) + min;
            keys.add(new Key(r1, i));
            keys.add(new Key(r2, i));
        }
        int r1 = random.nextInt(max - min + 1) + min;
        keys.add(new Key(r1, 10));
        return keys;
    }

    public List<Coin> createCoins(List<Key> keys) {
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

            // Check if there is no key at position (x, 22) before adding a coin
            if (!keyExistsAtPosition(keys, x, 22)) {
                coins.add(new Coin(x, 22));
            }
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

            // Check if there is no key at position (x, 10) before adding a coin
            if (!keyExistsAtPosition(keys, x, 10)) {
                coins.add(new Coin(x, 10));
            }
        }

        return coins;
    }

    private boolean keyExistsAtPosition(List<Key> keys, int x, int y) {
        for (Key key : keys) {
            if (key.getPosition().getX() == x && key.getPosition().getY() == y) {
                return true; // Key already exists at this position
            }
        }
        return false; // No key found at this position
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
        allWalls.addAll(floors);
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
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffdc00")); //Light Purple
        graphics.putString(new TerminalPosition(70, 2), "Time Left= " + Game.getTime());
        graphics.putString(new TerminalPosition(70, 4), "SCORE " + score);
        graphics.putString(new TerminalPosition(70, 6), "LEVEL " + level);
        graphics.putString(new TerminalPosition(20, 2), "HP  " + hero.getHp());
        for(int i = 0 ; i<=levelsFlooded ; i++){
            fillWater(i,graphics);
        }
        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        for (Floor floor : floors) {
            floor.draw(graphics);
        }
        for (Monster monster : monsters) {
            monster.draw(graphics);
        }
        for (Key key : keys) {
            key.draw(graphics);
        }
        for (Elevator elevator : elevators) {
            elevator.draw(graphics);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(graphics);
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
        for (Door door : doors) {
            door.draw(graphics);
        }
        hero.draw(graphics);
    }
    public void checkHeroCollisions() {
        if (checkMonsterCollision(hero.getPosition())) {
            hero.setHp(-20);
        }
        if (checkCoinCollision(hero.getPosition())) {
            score++;
        }
        checkKeyCollision(hero);

        if (checkBulletCollision(hero)) {
            hero.setHp(-10);
            hero.freeze();
        }
    }

    public boolean checkCoinCollision(Position heroPosition) {
        Iterator<Coin> coinIterator = coins.iterator();
        while (coinIterator.hasNext()) {
            Coin coin = coinIterator.next();
            if (heroPosition.samePosition(coin.getPosition())) {
                coinIterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean checkAllWalls(Position p) { //A FUNCAO TEM DE FICAR ASSIM PARA AS PORTAS DESAPARECEREM
        for (Wall wall : allWalls) {
            if (wall.checkColision(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMonsterCollision(Position heroPosition) {
        for (Monster monster : monsters) {
            Position monsterPosition = monster.getPosition();
            if (heroPosition.samePosition(monsterPosition)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkKeyCollision(Hero hero) {
        Iterator<Key> keysIterator = keys.iterator();
        while (keysIterator.hasNext()) {
            Key key = keysIterator.next();
            if (hero.getPosition().samePosition(key.getPosition())) {
                collectKeys();
                keysIterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean checkBulletCollision(Hero hero) {
        Iterator<Bullet> bulletsIterator = bullets.iterator();
        while (bulletsIterator.hasNext()) {
            Bullet bullet = bulletsIterator.next();
            if (hero.getPosition().samePosition(bullet.getPosition())) {
                bulletsIterator.remove();
                return true;
            }
        }
        return false;
    }

    public void openDoor(int i) {
        doors.get(i + 1).setOpen(true);
    }

    public List<Monster> createMonsters() {
        int min = 24;
        int max = 75;
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new NormalMonster(65, 24));
        for (int j = 1; j < normalMonsterspLevel.size(); j++) {
            for (int i = 0; i < normalMonsterspLevel.get(j); i++) {
                int ri = random.nextInt(max - min + 1) + min;
                monsters.add(new NormalMonster(ri, 24 - 4 * j));
            }
            for (int i = 0; i < shootingMonsterspLevel.get(j); i++) {
                int ri = random.nextInt(max - min + 1) + min;
                monsters.add(new ShootingMonster(ri, 24 - 4 * j));
            }
            for (int i = 0; i < speedMonsterspLevel.get(j); i++) {
                int ri = random.nextInt(max - min + 1) + min;
                monsters.add(new SpeedMonster(ri, 24 - 4 * j));
            }
        }
        return monsters;
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            monster.move(this);
        }
    }

    public List<Elevator> createElevators() {
        ArrayList<Elevator> elevators = new ArrayList<>();
        elevators.add((new Elevator(new Position(16, 25), new Position(22, 25))));
        elevators.add((new Elevator(new Position(77, 21), new Position(83, 21))));
        elevators.add((new Elevator(new Position(16, 17), new Position(22, 17))));
        elevators.add((new Elevator(new Position(77, 13), new Position(83, 13))));
        return elevators;
    }

    public boolean isOnElevator(Position position) {
        for (Elevator e : elevators) {
            ArrayList<Integer> temp = e.getPosition();
            int eX = position.getX();
            int eY = position.getY();
            if (eX >= temp.get(0) && eX <= temp.get(1) && eY == temp.get(2) - 1) {
                return true;
            }
        }
        return false;
    }

    public void collectKeys() {
        lastElevator = elevators.get(elevatorKeysCount / 2);
        openDoor(elevatorKeysCount);
        elevatorKeysCount++;
    }

    public void startElevator() {
        if (hero.isReady()) {
            lastElevator.runElevator(hero);
            level++;
            if(keys.size()%2 == 0) System.out.println("YOU LOST");
        }
    }

    public void activateShootingM() {
        for (Monster m : monsters) {
            m.action(this, hero);
        }
    }

    public void moveBullets() {
        if (bullets.isEmpty()) return;
        bullets.removeIf(bullet -> bullet.move(this));
    }

    public void fillWater(int level, TextGraphics graphics) {
        int yCord = 24 - 4 * level;
        int xCord = 16;
        int endYCord = yCord - 2;
        int endXCord = 83;

        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));;

        for (int y = yCord; y >= endYCord; y--) {
            for (int x = xCord; x <= endXCord; x++) {
                graphics.setCharacter(new TerminalPosition(x, y), '\u2588');
            }
        }
        graphics.setForegroundColor(TextColor.Factory.fromString("#9D0EB1"));
    }
    public int getScore(){
        return score;
    }
    public int getLevel(){
        return level;
    }
    public List<Door> getDoors(){
        return doors;
    }
    public void setFloodLevels(int i){levelsFlooded=i;}
}