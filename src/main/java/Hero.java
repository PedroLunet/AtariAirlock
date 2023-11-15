import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{
    public Hero(int x, int y){
        super(x, y);
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY() );
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#CD2C0C"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}
