import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {
    //Add direction
    private int direction;
    public long lastTimeMoved;
    public long speed;
    public Monster(int x,int y){
        super(x,y);
        direction = Math.random()<0.5 ? -1 : 1 ;
        lastTimeMoved = System.currentTimeMillis() ;
        speed = 250+(long) (Math.random() * 250); // Cada monstro move se com velocidade diferente
                                                  // 250 milisegundso + random por movimento
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));//blue for now
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
    public void move(Arena arena) {
        if (System.currentTimeMillis() - lastTimeMoved < speed) return;

        int newX = getPosition().getX() + direction;
        Position newPosition = new Position(newX, getPosition().getY());
        setPosition(newPosition); // Update the monster's position

        lastTimeMoved = System.currentTimeMillis(); // Update last move time
    }
    //Future Ideas
    //Maybe add monster types?
}
