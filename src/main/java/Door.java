import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Door extends Wall{

    private int height = 3;
    public Door(int x, int y) {
        super(x, y);
    }
    public Position getPosition() {
        return position;
    }
    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setCharacter(new TerminalPosition(x, y ), '\u2588');
        graphics.setCharacter(new TerminalPosition(x, y-1 ), '\u2588');
        graphics.setCharacter(new TerminalPosition(x, y-2 ), '\u2588');

    }
}
