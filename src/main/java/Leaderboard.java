import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    private List<Player> players;

    public Leaderboard() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void showLeaderboard() {
        sortPlayers(); // Ordenar os jogadores por pontuação

        System.out.println("Leaderboard:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - Score: " + player.getScore());
        }
    }

    private void sortPlayers() {
        players.sort(Comparator.comparingInt(Player::getScore).reversed());
    }
}
