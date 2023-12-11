import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpeedMonsterTest {
    @Test
    public void testMove() {
        Arena arena = new Arena(100, 28);
        SpeedMonster monster = new SpeedMonster(25, 24);
        arena.monsters.add(monster);
        monster.move(arena);
        Assertions.assertEquals(25, monster.getPosition().getX());
    }
}
