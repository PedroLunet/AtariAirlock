import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionsTests {

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
        Arena arena = new Arena(100,28);

        Hero hero = new Hero(25,24);
        arena.hero = hero;

        arena.coins.clear();
        Coin coin = new Coin(24,24);
        arena.coins.add(coin);
        hero.moveLeft(arena);

        boolean coinCollected = arena.checkCoinCollision(hero.getPosition());

        assertTrue(coinCollected, "Key should be collected");
        assertTrue(arena.coins.isEmpty(), "Keys list should be empty");
        assertEquals(1,arena.score);

    }
}
