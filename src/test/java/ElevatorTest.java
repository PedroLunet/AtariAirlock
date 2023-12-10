import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ElevatorTest {

    @Test
    public void testDraw() {
        TextGraphics mockGraphics = mock(TextGraphics.class);

        Position start = new Position(5, 5);
        Position end = new Position(10, 5);
        Elevator elevator = new Elevator(start, end);

        elevator.draw(mockGraphics);

        for (Position p : elevator.getElevatorPositions()) {
            verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            verify(mockGraphics).setCharacter(new TerminalPosition(p.getX(), p.getY()), '\u2580');
        }
    }

    @Test
    public void testGetPosition() {
        Position start = new Position(5, 5);
        Position end = new Position(10, 5);
        Elevator elevator = new Elevator(start, end);

        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(start.getX());
        expected.add(end.getX());
        expected.add(start.getY());

        assertEquals(expected, elevator.getPosition());
    }

    @Test
    public void testRunElevator() {
        Hero mockHero = mock(Hero.class);
        Position start = new Position(5, 5);
        Position end = new Position(10, 5);
        Elevator elevator = new Elevator(start, end);
        elevator.runElevator(mockHero);
        verify(mockHero).setPosition(new Position(mockHero.getPosition().getX(), 4));
    }
}