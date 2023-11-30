import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Key extends Element{

    //AS KEYS VAO DAR ACESSO AO ELEVADOR UM TIPO DE KEY
    //ABRE AS PAREDES , OUTRO LIGA O ELEVADOR
    private boolean keyType ; // keytype - parede ; !keytype - elevador

    public Key(int x,int y){
        super(x,y);
    }
    public Position getPosition(){
        return position;
    }
    //public boolean getType(){return keyType;} //Vai dar jeito na implementacao das key collisions

    @Override
    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setCharacter(new TerminalPosition(x,y), 'k');
    }
}
