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
    private List<Wall> walls;
    private List<Floor> floors;
    private List<Integer> monstersplevel = Arrays.asList(1, 2, 3, 3 , 4);
    private List<Monster> monsters = createMonsters();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(25, 23);
        walls = createWalls();
        floors = createFloors();
    }

    public void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowLeft) {
            hero.moveLeft(this);
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            hero.moveRight(this);
        } else if (key.getKeyType() == KeyType.ArrowUp) {
            hero.startJump();
        }
    }

    public List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            walls.add(new Wall(i, 0));
            walls.add(new Wall(i, 50));
        }
        for (int i = 0; i < 50; i++) {
            walls.add(new Wall(0, i));
            walls.add(new Wall(100, i));
        }
        return walls;
    }

    public List<Floor> createFloors(){
        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            floors.add(new Floor(i, 49));
        }
        return floors;
    }

    public void moveHero() { //move to hero class? // apenas atualiza a posicao do hero
        hero.move(this);
    }


    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#13022D")); //Dark Purple
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        graphics.setForegroundColor(TextColor.Factory.fromString("#9D0EB1")); //Light Purple

        for (Wall wall : walls){
            wall.draw(graphics);
        }

        for (Floor floor : floors){
            floor.draw(graphics);
        }

        for (Monster monster : monsters){
            monster.draw(graphics);
        }

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

