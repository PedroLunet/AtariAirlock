import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public abstract class Monster extends Element {
    private String mainChar;
    public Monster(int x,int y,String z){
        super(x,y);
        this.mainChar=z;
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#00008B"));//blue for now
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),this.mainChar);
    }
    public void move(Arena arena){}
    public void action(Arena arena,Hero hero){}
}
