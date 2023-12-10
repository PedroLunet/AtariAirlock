import javax.swing.*;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        showMainMenu();
    }

    public static void showMainMenu() throws IOException, InterruptedException {
        boolean validNameEntered = false;

        while (!validNameEntered) {
            String[] options = {"Iniciar Jogo", "Sair do Jogo", "Informacoes do Jogo"};

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opcao:",
                    "ATARI AIRLOCK",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    String playerName = JOptionPane.showInputDialog("Digite seu nome:");
                    if (playerName != null && !playerName.isEmpty()) {
                        startGame(playerName); // Inicia o jogo com o nome do jogador
                        validNameEntered = true; // Define que o nome é válido
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, insira um nome válido!");
                    }
                    break;
                case 1:
                    System.exit(0);
                    break;
                case 2:
                    showInstructions();
                    break;
                default:
                    break;
            }
        }
    }

    public static void showInstructions() {
        JOptionPane.showMessageDialog(null,
                "Instrucoes do Jogo:\n" +
                        "1. Use as setas para mover o personagem.\n" +
                        "2. Colete os itens para ganhar pontos.\n" +
                        "3. Evite os obstáculos para não perder pontos.\n\n" +
                        "Clique em 'OK' para voltar ao menu principal.",
                "INSTRUCOES DO JOGO",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void startGame(String playerName) throws IOException, InterruptedException {
        // Aqui inicia com o nome do jogador
        JOptionPane.showMessageDialog(null, "Iniciando o jogo para " + playerName);

        new Game(100, 28).run();
    }
}
