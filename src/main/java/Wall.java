import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Wall extends Element{


    public Wall(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#9D0EB1"));
        graphics.setCharacter(new TerminalPosition(x,y), '\u2588');
    }

    public boolean checkColision (Position p) {
        return p.samePosition(this.getPosition());
    }
}
