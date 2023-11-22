import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private final TerminalScreen screen;
    private Arena arena;


    public Game(int  width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);   // we don’t need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        TerminalSize terminalSize = new TerminalSize(width, height);
        arena = new Arena(width, height);
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException, InterruptedException {
        boolean gameStarted = false;
        long startingTime = 0;

        while (true) {
            draw();
            KeyStroke key = screen.pollInput();

            if (key != null) {
                if (!gameStarted) {
                    startingTime = System.currentTimeMillis();
                }
                gameStarted = true;

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                    System.out.println("Quit.");
                    break;
                }
                if (key.getKeyType() == KeyType.EOF) {
                    System.out.println("Game Closed.");
                    break;
                }
                processKey(key);
            }

            Thread.sleep(16);

            if (gameStarted) {
                arena.moveHero();
                arena.moveMonsters();

                Position heroPosition = arena.hero.getPosition();
                if (arena.checkMonsterCollision(heroPosition)) {
                    System.out.println("Game Over!");
                    gameStarted = false; // Aqui, ao detectar a colisão, o jogo é interrompido.
                }
            }
        }
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
}