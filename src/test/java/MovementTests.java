import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovementTests {

    private Arena arena;
    private Hero hero;

    @BeforeEach
    public void setUp() {
        arena = new Arena(100, 28);
        hero = new Hero(78, 24);
        arena.hero = hero;
    }

    @Test
    public void testHeroMovesLeft() {
        arena.processKey(new KeyStroke(KeyType.ArrowLeft));

        assertEquals(77, hero.getPosition().getX());
        assertEquals(24, hero.getPosition().getY());
    }

    @Test
    public void testHeroMovesRight() {
        arena.processKey(new KeyStroke(KeyType.ArrowRight));

        assertEquals(79, hero.getPosition().getX());
        assertEquals(24, hero.getPosition().getY());
    }
    @Test
    public void testHeroJump(){
        arena.processKey(new KeyStroke(KeyType.ArrowUp));
        assertEquals(1,hero.getIsJumping(), "Hero should start the jump");
        long jumpStart = hero.getJumpStart();
        hero.moveHero(arena,System.currentTimeMillis());
        if(hero.getIsJumping()==1) {
            assertEquals(new Position(78, 23), hero.getPosition());
        }
        if(hero.getIsJumping()==2){
            assertEquals(new Position(78, 22), hero.getPosition());
        }
        if(hero.getIsJumping()==3){
            assertEquals(new Position(78, 23), hero.getPosition());
        }
        if(hero.getIsJumping()==4){
            assertEquals(new Position(78, 24), hero.getPosition());
        }
    }
}
