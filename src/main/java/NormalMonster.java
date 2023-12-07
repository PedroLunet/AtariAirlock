import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class NormalMonster extends Monster {
    private int direction;
    private long lastTimeMoved;
    private long speed;
    public NormalMonster(int x, int y) {
        super(x,y,"\u263f");
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
            Position newP = new Position(newX, getPosition().getY());
            if(!arena.checkAllWalls(new Position(newP.getX(),newP.getY()+1)) && !arena.isOnElevator(newP)){
                direction *=-1;
                setPosition(new Position(newX+direction , getPosition().getY()));
                return ;
            }
            if (!arena.checkAllWalls(newP)) {
                setPosition(newP);
                moved = true;
                lastTimeMoved = System.currentTimeMillis();
            } else {
                direction *= -1;
            }
        }
    }
}
