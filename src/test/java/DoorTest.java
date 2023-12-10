import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.StyleSet;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.TabBehaviour;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {

    @Test
    public void testDrawWhenClosed() {
        Door door = new Door(5, 5);
        TextGraphics mockGraphics = new MockTextGraphics();

        door.draw(mockGraphics);
        assertEquals('\u2589', mockGraphics.getCharacter(new TerminalPosition(5, 5)));
        assertEquals('\u2589', mockGraphics.getCharacter(new TerminalPosition(5, 4)));
        assertEquals('\u2589', mockGraphics.getCharacter(new TerminalPosition(5, 3)));
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

    private class MockTextGraphics implements TextGraphics {
        @Override
        public TerminalSize getSize() {
            return null;
        }

        @Override
        public TextGraphics newTextGraphics(TerminalPosition topLeftCorner, TerminalSize size) throws IllegalArgumentException {
            return null;
        }

        @Override
        public TabBehaviour getTabBehaviour() {
            return null;
        }

        @Override
        public TextGraphics setTabBehaviour(TabBehaviour tabBehaviour) {
            return null;
        }

        @Override
        public TextGraphics fill(char c) {
            return null;
        }

        @Override
        public TextGraphics setCharacter(int column, int row, char character) {
            return null;
        }

        @Override
        public TextGraphics setCharacter(int column, int row, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics setCharacter(TerminalPosition position, char character) {
            return null;
        }

        @Override
        public TextGraphics setCharacter(TerminalPosition position, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics drawLine(TerminalPosition fromPoint, TerminalPosition toPoint, char character) {
            return null;
        }

        @Override
        public TextGraphics drawLine(TerminalPosition fromPoint, TerminalPosition toPoint, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics drawLine(int fromX, int fromY, int toX, int toY, char character) {
            return null;
        }

        @Override
        public TextGraphics drawLine(int fromX, int fromY, int toX, int toY, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics drawTriangle(TerminalPosition p1, TerminalPosition p2, TerminalPosition p3, char character) {
            return null;
        }

        @Override
        public TextGraphics drawTriangle(TerminalPosition p1, TerminalPosition p2, TerminalPosition p3, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics fillTriangle(TerminalPosition p1, TerminalPosition p2, TerminalPosition p3, char character) {
            return null;
        }

        @Override
        public TextGraphics fillTriangle(TerminalPosition p1, TerminalPosition p2, TerminalPosition p3, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics drawRectangle(TerminalPosition topLeft, TerminalSize size, char character) {
            return null;
        }

        @Override
        public TextGraphics drawRectangle(TerminalPosition topLeft, TerminalSize size, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics fillRectangle(TerminalPosition topLeft, TerminalSize size, char character) {
            return null;
        }

        @Override
        public TextGraphics fillRectangle(TerminalPosition topLeft, TerminalSize size, TextCharacter character) {
            return null;
        }

        @Override
        public TextGraphics drawImage(TerminalPosition topLeft, TextImage image) {
            return null;
        }

        @Override
        public TextGraphics drawImage(TerminalPosition topLeft, TextImage image, TerminalPosition sourceImageTopLeft, TerminalSize sourceImageSize) {
            return null;
        }

        @Override
        public TextGraphics putString(int column, int row, String string) {
            return null;
        }

        @Override
        public TextGraphics putString(TerminalPosition position, String string) {
            return null;
        }

        @Override
        public TextGraphics putString(int column, int row, String string, SGR extraModifier, SGR... optionalExtraModifiers) {
            return null;
        }

        @Override
        public TextGraphics putString(TerminalPosition position, String string, SGR extraModifier, SGR... optionalExtraModifiers) {
            return null;
        }

        @Override
        public TextGraphics putString(int column, int row, String string, Collection<SGR> extraModifiers) {
            return null;
        }

        @Override
        public TextGraphics putCSIStyledString(int column, int row, String string) {
            return null;
        }

        @Override
        public TextGraphics putCSIStyledString(TerminalPosition position, String string) {
            return null;
        }

        @Override
        public TextCharacter getCharacter(TerminalPosition position) {
            return null;
        }

        @Override
        public TextCharacter getCharacter(int column, int row) {
            return null;
        }

        @Override
        public TextColor getBackgroundColor() {
            return null;
        }

        @Override
        public TextGraphics setBackgroundColor(TextColor backgroundColor) {
            return null;
        }

        @Override
        public TextColor getForegroundColor() {
            return null;
        }

        @Override
        public TextGraphics setForegroundColor(TextColor foregroundColor) {
            return null;
        }

        @Override
        public TextGraphics enableModifiers(SGR... modifiers) {
            return null;
        }

        @Override
        public TextGraphics disableModifiers(SGR... modifiers) {
            return null;
        }

        @Override
        public TextGraphics setModifiers(EnumSet<SGR> modifiers) {
            return null;
        }

        @Override
        public TextGraphics clearModifiers() {
            return null;
        }

        @Override
        public EnumSet<SGR> getActiveModifiers() {
            return null;
        }

        @Override
        public TextGraphics setStyleFrom(StyleSet<?> source) {
            return null;
        }

    }
}
