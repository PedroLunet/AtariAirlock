import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element {
    public Hero(int x, int y) {
        super(x, y);
    }

    private int isJumping = 0;
    private long jumpStart;
    public void moveRight(Arena arena) {
        int newX = position.getX() + 1;
        Position newPosition = new Position(newX, position.getY());
        setPosition(newPosition); // Update the hero's position
    }

    public void moveLeft(Arena arena) {
        int newX = position.getX() - 1;
        Position newPosition = new Position(newX, position.getY());
        setPosition(newPosition); // Update the hero's position
    }


    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#CD2C0C"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
    public void startJump(){
        if(isJumping>0) return ;
        isJumping=1;
        jumpStart = System.currentTimeMillis();
    }
    public void move(Arena arena) {
        int jumpHeight = 1; //for now
        long currentTime = System.currentTimeMillis();
        long dt = currentTime - jumpStart;
        Position startingP = getPosition();
        int speed = 5;
        if (isJumping == 0) return;
        if (isJumping == 1) {
            int newY = startingP.getY() - jumpHeight;
            setPosition(new Position(startingP.getX(), newY));
            isJumping = 2;
            return;
        }
        if (isJumping == 2 && dt > 100*speed) {
            int newY = startingP.getY() - jumpHeight;
            setPosition(new Position(startingP.getX(), newY));
            isJumping = 3;
            return;
        }
        if (isJumping == 3 && dt > 200*speed) {
            int newY = startingP.getY() + jumpHeight;
            setPosition(new Position(startingP.getX(), newY));
            isJumping = 4;
            return;
        }
        if (isJumping == 4 && dt > 300*speed) {
            int newY = startingP.getY() + jumpHeight;
            setPosition(new Position(startingP.getX(), newY));
            isJumping = 0;
            return;
        }
    }
}
