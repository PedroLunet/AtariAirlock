import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardTests {
    private Leaderboard leaderboard;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        leaderboard = new Leaderboard();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddPlayer() {
        Player player = new Player("John", 100);
        leaderboard.addPlayer(player);
        assertEquals(1, leaderboard.getPlayers().size());
    }

    @Test
    public void testSortPlayers() {
        Player player1 = new Player("John", 100);
        Player player2 = new Player("Jane", 200);
        leaderboard.addPlayer(player1);
        leaderboard.addPlayer(player2);
        leaderboard.sortPlayers();
        assertEquals("Jane", leaderboard.getPlayers().get(0).getName());
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}