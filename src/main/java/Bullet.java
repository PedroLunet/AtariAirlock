import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Bullet extends Element {
    private int direction;
    private int speed;
    private long lastTimeMoved=System.currentTimeMillis();
    public Bullet(int x, int y , int direction , int speed) {
        super(x, y);
        this.speed=speed;
        this.direction=direction;
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));//white for now
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "\u25B4");
    }
    public boolean move(Arena arena) {
        boolean toRemove=false;
        long currentTime = System.currentTimeMillis();
        if(arena.bullets.isEmpty()) return toRemove;
        if (currentTime - lastTimeMoved > speed) {
            position.setX(position.getX() + direction);
            Position newp= new Position(position.getX() + direction,this.getPosition().getY());
            if(arena.checkWalls(newp)) toRemove = true;
            lastTimeMoved = currentTime;
        }
        return toRemove;
    }
}
