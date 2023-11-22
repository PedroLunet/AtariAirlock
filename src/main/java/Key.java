import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Key extends Element{

    //AS KEYS VAO DAR ACESSO AO ELEVADOR UM TIPO DE KEY
    //ABRE AS PAREDES , OUTRO LIGA O ELEVADOR
    private boolean keyType ; // keytype - parede ; !keytype - elevador

    public Key(int x,int y,boolean type){
        super(x,y);
        keyType = type;
    }
    public Position getPosition(){
        return position;
    }
    public boolean getType(){return keyType;} //Vai dar jeito na implementacao das key collisions

    @Override
    public void draw(TextGraphics graphics) {
        int x = position.getX();
        int y = position.getY();
        graphics.setForegroundColor(keyType ? TextColor.Factory.fromString("#FFFF00") :
                TextColor.Factory.fromString("#FFA500"));

        TerminalPosition p = new TerminalPosition(x,y);
        char c = keyType ? '\u258E' : '\u258D';
        graphics.setCharacter(p,c);
    }
}
