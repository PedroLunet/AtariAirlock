import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ShootingMonster extends Monster {
    private long lasTimeShot=0;
    public ShootingMonster(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Arena arena) {
        return ;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));//blue for now
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "S");
    }

    public void monsterShoot(Hero hero){
        if(System.currentTimeMillis()-lasTimeShot>3000) {
            int direction = hero.getPosition().getX() > this.getPosition().getX() ? 1 : -1;
            Bullet b = new Bullet(this.getPosition().getX() + direction , this.getPosition().getY(), direction, 100);
            Arena.bullets.add(b);
            lasTimeShot=System.currentTimeMillis();
        }
    }
}
