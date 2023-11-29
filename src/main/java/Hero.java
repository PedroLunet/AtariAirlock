import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element {
    public Hero(int x, int y) {
        super(x, y);
    }
    private int hp=100;
    private int isJumping = 0;
    private long jumpStart;
    private boolean ready=false;
    private boolean isFrozen=false;
    private long freezeStart = 0;
    public void moveRight(Arena arena) {
        if(isFrozen) return;
        int newX = position.getX() + 1;
        Position newPosition = new Position(newX, position.getY());

        if (!arena.checkWalls(newPosition)) {
            setPosition(new Position(newX, position.getY()));
        }
    }
    public void moveLeft(Arena arena) {
        if(isFrozen) return;
        int newX = position.getX() - 1;
        Position newPosition = new Position(newX, position.getY());

        if (!arena.checkWalls(newPosition)) {
            setPosition(new Position(newX, position.getY()));
        }
    }
    public void moveHero(Arena arena) {
        if(isFrozen) return;
        this.jump(System.currentTimeMillis());
        Position heroPosition = this.getPosition();
        arena.checkCollisions();
        if(arena.isOnElevator(this)) ready=true; else ready = false;
    }
    public boolean isReady(){
        return ready;
    }


    public void draw(TextGraphics graphics) {
        if(isFrozen){
            graphics.setForegroundColor(TextColor.Factory.fromString("#ADD8E6"));
        }
        else{
            graphics.setForegroundColor(TextColor.Factory.fromString("#CD2C0C"));
        }
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
    public void startJump(){
        if(isJumping>0) return ;
        isJumping=1;
        jumpStart = System.currentTimeMillis();
    }
    public void jump(long currentTime) {
        int jumpHeight = 1; //for now
        long dt = currentTime - jumpStart;
        Position startingP = getPosition();
        int speed = 3;
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
    public void freeze(){
        isFrozen=true;
        freezeStart= System.currentTimeMillis();
    }
    public void updateFreezeState(long currentTime) {
        if (isFrozen && currentTime - freezeStart > 2000) {
            isFrozen = false;
        }
    }
    public void setHp(int i){
        System.out.println("OUCH!");
        hp+=i;
    }
}