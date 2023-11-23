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
    private boolean ready=false;
    public void moveRight(Arena arena) {
        int newX = position.getX() + 1;
        Position newPosition = new Position(newX, position.getY());

        if (!arena.checkWalls(newPosition)) {
            setPosition(new Position(newX, position.getY()));
        }
    }

    public void moveLeft(Arena arena) {
        int newX = position.getX() - 1;
        Position newPosition = new Position(newX, position.getY());

        if (!arena.checkWalls(newPosition)) {
            setPosition(new Position(newX, position.getY()));
        }
    }
    public void moveHero(Arena arena) { // apenas atualiza a posicao do hero
        this.jump();
        Position heroPosition = this.getPosition();
        if (arena.checkMonsterCollision(heroPosition)) {
            System.out.println("Game Over!");
            // Se houver colisão, o jogo termina
            System.exit(0); // Esta é uma forma simples de interromper o jogo. Dependendo do ambiente e da estrutura, pode ser necessário usar outro método para interromper o jogo.
        }
        if(arena.checkKeyCollision(this)){
            //WE CHANGE THIS TO ELEVATOR PROPERTIES
            //if(lastKeyType) open walls
            //if(!lastKeyType) activate elevator
            System.out.println("GOT A KEY"); //TEMPORARY
        }
        if(arena.isOnElevator(this)) ready=true; else ready = false;
    }
    public boolean isReady(){
        return ready;
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
    public void jump() {
        int jumpHeight = 1; //for now
        long currentTime = System.currentTimeMillis();
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
}
