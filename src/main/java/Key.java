import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Key extends Element{

    public Key(int x,int y){
        super(x,y);
    }
    public Position getPosition(){
        return position;
    }
    @Override
    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#fffb00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.setCharacter(new TerminalPosition(x,y), '\u2640');
    }
}
