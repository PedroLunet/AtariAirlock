import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Floor extends Wall{
    public Floor(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setCharacter(new TerminalPosition(x,y), '\u2580');
    }
}