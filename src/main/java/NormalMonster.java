import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class NormalMonster extends Monster {
    private int direction;
    private long lastTimeMoved;
    private long speed;
    public NormalMonster(int x, int y) {
        super(x,y,"M");
        direction = Math.random()<0.5 ? -1 : 1 ;
        lastTimeMoved = System.currentTimeMillis() ;
        speed = 250+(long) (Math.random() * 250);
    }
    @Override
    public void move(Arena arena){
        if(System.currentTimeMillis()-lastTimeMoved < speed) return ;
        boolean moved= false;
        while (!moved) {
            int newX = getPosition().getX() + direction;
            Position newPosition = new Position(newX, getPosition().getY());

            if (!arena.checkWalls(newPosition)) {
                setPosition(newPosition);
                moved = true;
                lastTimeMoved = System.currentTimeMillis();
            } else {
                direction *= -1;
            }
        }
    }
}
