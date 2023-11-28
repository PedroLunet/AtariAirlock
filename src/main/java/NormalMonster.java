import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class NormalMonster extends Monster {
    private int direction;
    public long lastTimeMoved;
    public long speed;

    public NormalMonster(int x, int y) {
        super(x, y);
    }
}
