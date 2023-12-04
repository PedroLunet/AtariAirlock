import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Door extends Wall{
    private boolean isOpen = false;
    private int height = 3;
    public Door(int x, int y) {
        super(x, y);
    }
    public Position getPosition() {
        return position;
    }
    public void draw(TextGraphics graphics) {
        if (isOpen) return;
        int x = position.getX();
        int y = position.getY();
        graphics.setCharacter(new TerminalPosition(x, y ), '\u2588');
        graphics.setCharacter(new TerminalPosition(x, y-1 ), '\u2588');
        graphics.setCharacter(new TerminalPosition(x, y-2 ), '\u2588');

    }
    public void setOpen(boolean open) {
        isOpen = open;
    }
    public boolean checkColision (Position p) {
        if (isOpen) return false;
        for(int i = 0 ; i<3 ; i++){
            if(p.samePosition(
                    new Position(this.getPosition().getX(),this.getPosition().getY()-i)
                    ))
                return true;
        }
        return false;
    }

}
