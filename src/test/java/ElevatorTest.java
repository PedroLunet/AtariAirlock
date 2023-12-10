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
        // Mock TextGraphics
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);


        Position start = new Position(5, 10);
        Position end = new Position(10, 10);
        Elevator elevator = new Elevator(start, end);


        elevator.draw(textGraphics);

        for (int pos : elevator.getPosition()) {
            verify(textGraphics, times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            verify(textGraphics, times(1)).setCharacter(new TerminalPosition(pos, start.getY()), '\u2580');
        }
    }
}
