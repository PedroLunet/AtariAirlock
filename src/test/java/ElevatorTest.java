import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ElevatorTest {
    @Test
    public void testDraw() {
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        Elevator elevator = new Elevator(new Position(1, 1), new Position(2, 2));
        elevator.draw(graphics);
        verify(graphics, times(2)).setForegroundColor(Mockito.any(TextColor.class));
        verify(graphics, times(2)).setCharacter(Mockito.any(TerminalPosition.class), Mockito.anyChar());
    }

    @Test
    public void testGetPosition() {
        Elevator elevator = new Elevator(new Position(1, 1), new Position(2, 2));
        elevator.getPosition();
    }
}
