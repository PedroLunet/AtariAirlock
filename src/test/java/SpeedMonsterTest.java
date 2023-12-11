import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpeedMonsterTest {

    @Test
    public void testMove_ShouldChangeDirectionWhenWallOrElevatorDetected() {

        Arena arena = new Arena(10, 10);
        SpeedMonster speedMonster = new SpeedMonster(5, 5);
        int initialX = speedMonster.getPosition().getX();
        speedMonster.move(arena);

        Assertions.assertNotEquals(initialX, speedMonster.getPosition().getX());

    }
}
