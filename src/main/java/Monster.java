import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element {

    public Monster(int x,int y){
        super(x,y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));//blue for now
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    //Future Ideas
    //Maybe add monster types?
}
