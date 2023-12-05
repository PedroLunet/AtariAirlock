import javax.swing.*;

public class GameLogic {
    public void startGame(String playerName) {
        System.out.println("Iniciando o jogo para o jogador: " + playerName);
    }

    public void showDevelopersInfo() {
        // Adicionando os nomes dos desenvolvedores
        String developersInfo = "Desenvolvedores do jogo:\n";
        developersInfo += "Pedro Lunet\n";
        developersInfo += "Antonio Cunha\n";
        developersInfo += "Allan Santos";

        JOptionPane.showMessageDialog(null, developersInfo, "Desenvolvedores", JOptionPane.INFORMATION_MESSAGE);
    }
}
