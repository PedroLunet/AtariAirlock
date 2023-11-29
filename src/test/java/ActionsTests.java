import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
