import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element {
    public Hero(int x, int y) {
        super(x, y);
    }
    private int hp=100;
    private boolean canMove = true;
    private int isJumping = 0;
    private long jumpStart;
    private boolean ready=false;
    private boolean isFrozen=false;
    private long freezeStart = 0;
    private long lastTimeHit = 0;
    public void moveRight(Arena arena) {
        if(isFrozen) return;
        int newX = position.getX() + 1;
        Position newPosition = new Position(newX, position.getY());

        if (!arena.checkAllWalls(newPosition)) {
            setPosition(new Position(newX, position.getY()));
        }
    }
    public void moveLeft(Arena arena) {
        if(isFrozen) return;
        int newX = position.getX() - 1;
        Position newPosition = new Position(newX, position.getY());
        if (!arena.checkAllWalls(newPosition)) {
            setPosition(new Position(newX, position.getY()));
        }
    }
    public void moveHero(Arena arena , long currentTime) {
        updateFreezeState(currentTime);
        if(isFrozen) return;
        this.jump(arena, currentTime);
        Position heroPosition = this.getPosition();
        arena.checkHeroCollisions();
        if(arena.isOnElevator(this)) ready=true; else ready = false;
    }
    public void setHp(int i){
        if(System.currentTimeMillis()-lastTimeHit<500) return ;
        lastTimeHit=System.currentTimeMillis();
        System.out.println("OUCH!");
        hp+=i;
    }
    public int getHp(){
        return this.hp;
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
    public void jump(Arena arena ,long currentTime) {
        int jumpHeight = 1; //for now
        long dt = currentTime - jumpStart;
        Position startingP = getPosition();
        int speed = 2;
        if (isJumping == 0) return;
        if (isJumping == 1) {
            int newY = startingP.getY() - jumpHeight;
            this.setPosition(new Position(startingP.getX(), newY));
            isJumping = 2;
            return;
        }
        if (isJumping == 2 && dt > 100*speed) {
            int newY = startingP.getY() - jumpHeight;
            this.setPosition(new Position(startingP.getX(), newY));
            isJumping = 3;
            return;
        }
        if (isJumping == 3 && dt > 200*speed) {
            int newY = startingP.getY() + jumpHeight;
            this.setPosition(new Position(startingP.getX(), newY));
            isJumping = 4;
            return;
        }
        if (isJumping == 4 && dt > 300*speed) {
            int newY = startingP.getY() + jumpHeight;
            this.setPosition(new Position(startingP.getX(), newY));
            isJumping = 0;
            return;
        }
    }
    public boolean isReady(){
        return ready;
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
}