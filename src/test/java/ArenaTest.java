import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.List;
import static org.mockito.Mockito.*;

public class ArenaTest {

    @Test
    public void testMoveMonsters() throws NoSuchFieldException, IllegalAccessException {
        Arena arena = new Arena(100, 50);
        Monster mockMonster = mock(Monster.class);
        Field field = Arena.class.getDeclaredField("monsters");
        field.setAccessible(true);
        List<Monster> monsters = (List<Monster>) field.get(arena);
        monsters.add(mockMonster);

        arena.moveMonsters();

        verify(mockMonster, times(1)).move(arena);
    }
}
