import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.*;

public class Elevator {
    private List<Position> elevator=new ArrayList<>();
    private Position startingP;
    private Position endingP;
    private boolean isActivated = false;
    public Elevator(Position start , Position end){
        this.startingP = start;
        this.endingP = end;
        for(int i = startingP.getX() ; i<endingP.getX()+1 ; i++){
            elevator.add(new Position(i , startingP.getY()));
        }
    }
    public void draw(TextGraphics graphics) {
        for(Position p : elevator){
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.setCharacter(new TerminalPosition(p.getX(), p.getY()),'\u2580');
        }
    }
    public ArrayList<Integer> getPosition(){
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(startingP.getX());
        temp.add(endingP.getX());
        temp.add(startingP.getY());
        return temp;
    }
    public void activateElevator(){
        isActivated = true;
    }
}
