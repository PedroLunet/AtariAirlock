import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementTests {

    private Arena arena;
    private Hero hero;

    @BeforeEach
    public void setUp() {
        arena = new Arena(100, 28);
        hero = new Hero(50, 24);
        arena.hero = hero;
    }

    @Test
    public void testHeroMovesLeft() {
        arena.processKey(new KeyStroke(KeyType.ArrowLeft));

        assertEquals(49, hero.getPosition().getX());
        assertEquals(24, hero.getPosition().getY());
    }

    @Test
    public void testHeroMovesRight() {
        arena.processKey(new KeyStroke(KeyType.ArrowRight));

        assertEquals(51, hero.getPosition().getX());
        assertEquals(24, hero.getPosition().getY());
    }
}
