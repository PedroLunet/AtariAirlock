import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    public Hero hero;
    private int score=0; // EACH LEVEL THE HERO REACHES THE SCORE WILL INCREMENT
    private List<Position> vwalls;
    private List<Position> hwalls;
    private List<Position> allWalls = new ArrayList<>();;
    private List<Integer> monstersplevel = Arrays.asList(1, 2, 3, 3 , 4);
    private List<Monster> monsters = createMonsters();
    private List<Key> keys = createKeys();
    private int elevatorKeysC = 0;
    private int wallKeysC = 0;
    private boolean lastKeyType ;
    private List<Elevator> elevators = createElevators() ;
    private Elevator lastElevator = elevators.get(0);
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(25, 23);
        this.vwalls = new ArrayList<>();
        this.hwalls = new ArrayList<>();
        initializeWalls();
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
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#13022D")); //Dark Purple
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#9D0EB1")); //Light Purple
        graphics.putString(new TerminalPosition(70, 5), "Time Left= " + Game.getTime());
        graphics.putString(new TerminalPosition(20,5),"SCORE " + score);
        for (Position wall : vwalls) {
            graphics.setCharacter(wall.getX(), wall.getY(), '\u2588');
        }
        for (Position wall : hwalls) {
            graphics.setCharacter(wall.getX(), wall.getY(), '\u2580');
        }
        for (Monster monster : monsters){ monster.draw(graphics);}
        hero.draw(graphics);
        for (Key key : keys){ key.draw(graphics);}
        for(Elevator elevator : elevators) {elevator.draw(graphics);}

    }
    private void initializeWalls() {
        int topDistance = 8;
        int bottomDistance = 2;
        int sideDistance = 15;
        int elevatorWidth = 8;
        int floorSeparation = 2;

        for (int x = sideDistance; x < width - sideDistance; x++) {
            vwalls.add(new Position(x, height - bottomDistance - 2));
        }

        for (int y = topDistance + 1; y < height - bottomDistance - 2; y++) {
            vwalls.add(new Position(sideDistance, y));
            vwalls.add(new Position(sideDistance + elevatorWidth - 1, y));
            vwalls.add(new Position(width - sideDistance - 1, y));
            vwalls.add(new Position(width - sideDistance - elevatorWidth, y));
            if ((y - topDistance - 1) % (floorSeparation + 1) == 0) {
                for (int x = sideDistance + elevatorWidth; x < width - sideDistance - elevatorWidth; x++) {
                    hwalls.add(new Position(x, y));
                }
            }
        }
    }
    public boolean checkWalls(Position p){
        if(allWalls.isEmpty()){ allWalls.addAll(vwalls); allWalls.addAll(hwalls);}
        for (Position wall : allWalls) {
            if(p.samePosition(wall)){
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
    public boolean checkKeyCollision(Hero hero){
        Position heroP = hero.getPosition();
        for(Key key : keys){
            Position keyP = key.getPosition();
            if(heroP.samePosition(keyP)){
                lastKeyType = key.getType();
                collectKeys();
                keys.remove(key);
                return true;
            }
        }
        return false;
    }

   public List<Monster> createMonsters() {
       int min = 23; // Parede da esquerda
       int max = 76; // Parede da direita
       Random random = new Random();
       ArrayList<Monster> monsters = new ArrayList<>();
       monsters.add(new Monster(65, 23));
       for (int j = 1; j < monstersplevel.size(); j++) {
           for (int i = 0; i < monstersplevel.get(j); i++) {
               int ri = random.nextInt(max - min + 1) + min;
               monsters.add(new Monster(ri, 23 - 3 * j));
           }
       }
       return monsters;
   }
   public void moveMonsters(){
        for(Monster monster : monsters){
            monster.move(this);
        }
   }
   public List<Key> createKeys(){
        //ESTA FUNCAO ESTA FEITA PARA A ARENA ANTIGA , ALTERAR LINHAS 118 119 122 E 126(Y COORDINATE)
        int min = 23; //Parede esquerda
        int max = 76; //Parede direita
        Random random = new Random();
        ArrayList<Key> keys = new ArrayList<>();
        for(int j = 0 ; j< 4 ; j++){
            for(int i = 0 ; i<2 ; i++){
                int ri = random.nextInt(max - min + 1) + min;
                boolean type= i==0 ;
                keys.add(new Key (ri, 23-3*j - 1 , type));
            }
        }
        return keys;
   }
   public List<Elevator> createElevators(){ //TODO
        ArrayList<Elevator> elevators = new ArrayList<>();
        elevators.add((new Elevator(new Position(37,24),new Position(44,24))));
        elevators.add((new Elevator(new Position(47,21),new Position(54,21))));
        elevators.add((new Elevator(new Position(57,18),new Position(64,18))));
        elevators.add((new Elevator(new Position(67,15),new Position(74,15))));
        return elevators;
    }
   public boolean isOnElevator(Hero hero){
        for(Elevator e : elevators){
            ArrayList<Integer> temp = e.getPosition();
            int heroX = hero.getPosition().getX();
            int heroY = hero.getPosition().getY();
            if(heroX >= temp.get(0) && heroX <= temp.get(1) && heroY == temp.get(2)-1){
                System.out.println("Im on the elevator"+ e );
                return true;
            }
        }
       return false;
   }
   public void collectKeys(){
        if(!lastKeyType) {
            elevators.get(elevatorKeysC).activateElevator();
            lastElevator=elevators.get(elevatorKeysC);
            elevatorKeysC++;
        }
        //else...
   }
   public void startElevator(){
        if(hero.isReady() && lastElevator.getActiveStatus()){
            lastElevator.runElevator(hero);
            score++;
        }
   }
}

