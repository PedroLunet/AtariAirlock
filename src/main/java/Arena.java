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
    private List<Position> vwalls;
    private List<Position> hwalls;
    private List<Position> allWalls = new ArrayList<>();;
    private List<Integer> monstersplevel = Arrays.asList(1, 2, 3, 3 , 4);
    private List<Monster> monsters = createMonsters();
    private List<Key> keys = createKeys();
    private boolean lastKeyType ;

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

    public void moveHero() { // apenas atualiza a posicao do hero
        hero.move(this);
        Position heroPosition = hero.getPosition();
        if (checkMonsterCollision(heroPosition)) {
            System.out.println("Game Over!");
            // Se houver colisão, o jogo termina
            System.exit(0); // Esta é uma forma simples de interromper o jogo. Dependendo do ambiente e da estrutura, pode ser necessário usar outro método para interromper o jogo.
        }
        if(checkKeyCollision(hero)){
            //WE CHANGE THIS TO ELEVATOR PROPERTIES
            //if(lastKeyType) open walls
            //if(!lastKeyType) activate elevator
            System.out.println("GOT A KEY"); //TEMPORARY
        }
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
                keys.remove(key);
                return true;
            }
        }
        return false;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#13022D")); //Dark Purple
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#9D0EB1")); //Light Purple

        for (Position wall : vwalls) {
            graphics.setCharacter(wall.getX(), wall.getY(), '\u2588');
        }
        for (Position wall : hwalls) {
            graphics.setCharacter(wall.getX(), wall.getY(), '\u2580');
        }
        for (Monster monster : monsters){ monster.draw(graphics);}
        hero.draw(graphics);
        for (Key key : keys){ key.draw(graphics);}
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
}

