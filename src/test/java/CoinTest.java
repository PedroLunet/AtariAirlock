import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq; // Importe eq do Mockito
import static org.mockito.Mockito.*;

class CoinTest {
    private Coin coin;
    private TextGraphics textGraphicsMock;

    @BeforeEach
    void setUp() {
        coin = new Coin(5, 10);
        textGraphicsMock = mock(TextGraphics.class);
    }

    @Test
    void testGetPosition() {
        Position position = coin.getPosition();
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    @Test
    void testDraw() {
        coin.draw(textGraphicsMock);
        verify(textGraphicsMock, times(1)).setForegroundColor(any());
        verify(textGraphicsMock, times(1)).enableModifiers(any());
        verify(textGraphicsMock, times(1)).setCharacter(any(TerminalPosition.class), eq('o')); // Verifica os argumentos corretos usando eq()
    }
}
