import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {

    @Test
    public void testDrawWhenClosed() {

        Door door = new Door(5, 5);


        TextGraphics textGraphicsMock = mock(TextGraphics.class);

        door.draw(textGraphicsMock);

        verify(textGraphicsMock, times(1)).setForegroundColor(any());
        verify(textGraphicsMock, times(1)).setCharacter(new TerminalPosition(5, 5), '\u2589');
        verify(textGraphicsMock, times(1)).setCharacter(new TerminalPosition(5, 4), '\u2589');
        verify(textGraphicsMock, times(1)).setCharacter(new TerminalPosition(5, 3), '\u2589');
    }

    @Test
    public void testCheckCollisionWhenClosed() {

        Door door = new Door(5, 5);

        assertTrue(door.checkColision(new Position(5, 5)));
        assertTrue(door.checkColision(new Position(5, 4)));
        assertTrue(door.checkColision(new Position(5, 3)));

        assertFalse(door.checkColision(new Position(6, 5)));
        assertFalse(door.checkColision(new Position(5, 6)));
        assertFalse(door.checkColision(new Position(4, 5)));
    }
}
