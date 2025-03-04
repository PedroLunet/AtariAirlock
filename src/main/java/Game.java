import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private final TerminalScreen screen;
    private Arena arena;
    private static long timeLeft=60;
    private Leaderboard leaderboard;
    private static boolean gameStarted=false;

    public Game(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        TerminalSize terminalSize = new TerminalSize(width, height);
        arena = new Arena(width, height);
        leaderboard = new Leaderboard();
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException, InterruptedException {
        boolean openArena = false;
        long startingTime = 0;
        String playerName = "";
        int levelsFlooded = 0;
        long lastUpdated = 0;
        while (playerName.isEmpty()) {
            System.out.println("Please enter your name: ");
            Scanner scanner = new Scanner(System.in);
            playerName = scanner.nextLine();
        }
        while (true) {
            draw();
            KeyStroke key = screen.pollInput(); //Em vez de esperar por input o while corre sempre poll != read
            if (key != null) {
                if (!gameStarted) startingTime = System.currentTimeMillis();
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
                if(timeLeft == 0) {
                    screen.close();
                    System.out.println("Game Over");
                    Player player = new Player(playerName, arena.getScore());
                    leaderboard.addPlayer(player);
                    break;
                }
            }
            Thread.sleep(16);//16 milliseconds == 1000ms / 60 fps // while+lento
            long currentTime=System.currentTimeMillis();
            if (gameStarted) {
                if(!openArena) {
                    arena.getDoors().get(0).setOpen(true);
                    openArena = true;
                }
                long timePassed = (currentTime - startingTime) / 1000;
                timeLeft = 60 - timePassed;
                if(timeLeft%15 == 0 && timeLeft!=60 && System.currentTimeMillis()>=lastUpdated+15000){
                    lastUpdated=System.currentTimeMillis();
                    arena.setFloodLevels(levelsFlooded);
                    checkDrown(levelsFlooded);
                    levelsFlooded++;
                }
                if(timeLeft == 0 ){
                    screen.close();
                    System.out.println("GAME OVER !");
                    break;}
                if(arena.getLevel()==4){
                    screen.close();
                    System.out.println("YOU ESCAPED ! ");
                    System.out.println("You did so in "+ (50-timeLeft)+ " seconds with a score of "+ arena.getScore() +" !" );
                }
                if(arena.hero.getHp()<=0){
                    screen.close();
                    System.out.println("YOU DIED ");
                }
                arena.hero.moveHero(arena,currentTime);
                arena.moveMonsters();
                arena.startElevator();
                arena.activateShootingM();
                arena.moveBullets();
                arena.checkHeroCollisions();
            }
            Player player = new Player(playerName, arena.getScore());
            //System.out.println("Player: " + player.getName() + " - Score: " + player.getScore());
        }

        leaderboard.showLeaderboard(); // Mostrar a leaderboard ao final do jogo

    }
    public static long getTime(){ return timeLeft;}
    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
    private boolean checkDrown(int levels ) throws IOException {
        if(arena.getLevel()==levels){
            System.out.println("YOU DROWNED");
            screen.close();
            return true;
        }
        return false;

    }
}