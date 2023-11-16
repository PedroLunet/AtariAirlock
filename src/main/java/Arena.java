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
    private Hero hero;
    private List<Position> vwalls;
    private List<Position> hwalls;
    private List<Position> allWalls = new ArrayList<>();;
    private List<Integer> monstersplevel = Arrays.asList(1, 2, 3, 3 , 4);
    private List<Monster> monsters = createMonsters();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(25, 23);
        this.vwalls = new ArrayList<>();
        this.hwalls = new ArrayList<>();
        initializeWalls();
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(hero.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(hero.moveRight());
        }
        //hero jump on arrowup
    }

    public void moveHero(Position position) { //move to hero class?
        hero.setPosition(position);
        System.out.println(hero.getPosition()); //not needed
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
            if(p.samePosition(wall,p)){
                return true;
            }
        }
        return false; // No collision
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
}

