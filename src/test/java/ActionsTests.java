import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionsTests {
    private Arena arena;
    private Hero hero;

    @BeforeEach
    public void setUp() {
        arena = new Arena(100, 28);
        hero = new Hero(25, 24);
        arena.hero = hero;
    }

    @Test
    public void testKeyCollection() {

        Arena arena = new Arena(100, 28);

        Hero hero = new Hero(25, 24);
        arena.hero = hero;

        Key key = new Key(25, 24);
        arena.keys.clear();
        arena.keys.add(key);

        boolean keyCollected = arena.checkKeyCollision(hero);

        assertTrue(keyCollected, "Key should be collected");
        assertTrue(arena.keys.isEmpty(), "Keys list should be empty");
    }
    @Test
    public void testCoinCollection(){

        arena.coins.clear();
        Coin coin = new Coin(24,24);
        arena.coins.add(coin);

        boolean coinCollected = arena.checkCoinCollision(hero.getPosition());
        assertFalse(coinCollected,"Coin shouldnt be collected");

        hero.moveLeft(arena);

        arena.checkHeroCollisions();

        assertTrue(arena.coins.isEmpty(), "Coins list should be empty");
        assertEquals(1,arena.getScore(), "Score should get incremented");

    }
    @Test
    public void testBulletCollision(){
        arena.bullets.clear();
        Bullet bullet = new Bullet(25,24,-1,100);
        arena.bullets.add(bullet);
        arena.moveBullets();
        arena.checkHeroCollisions();
        assertEquals(90,hero.getHp(),"Hero should get damaged");
        assertTrue(arena.bullets.isEmpty(),"Bullet should dissapear from the screen");
        assertTrue(hero.isHeroFrozen(), "Hero should be frozen");
    }

}
