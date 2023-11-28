import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ShootingMonster extends Monster {
    private long lasTimeShot=0;
    public ShootingMonster(int x, int y) {
        super(x,y,"S");
    }
    public void action(Arena arena ,Hero hero){
        if(hero.getPosition().getY()!=this.getPosition().getY()) return ;
        if(System.currentTimeMillis()-lasTimeShot>3000) {
            int direction = hero.getPosition().getX() > this.getPosition().getX() ? 1 : -1;
            Bullet b = new Bullet(this.getPosition().getX() + direction , this.getPosition().getY(), direction, 100);
            arena.bullets.add(b);
            lasTimeShot=System.currentTimeMillis();
        }
    }
}
