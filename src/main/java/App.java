import java.io.IOException;
import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        showMainMenu();

        // Continuar com a execução do jogo
        new Game(100, 28).run();
    }

    public static void showMainMenu() {
        GameLogic gameLogic = new GameLogic();

        String[] options = {"Iniciar Jogo", "Sair do Jogo", "Informacoes dos Desenvolvedores"};
        int choice = JOptionPane.showOptionDialog(null, "Selecione uma opcao:", "Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                String playerName = JOptionPane.showInputDialog("Digite seu nome:");
                gameLogic.startGame(playerName);
                break;
            case 1:
                System.exit(0);
                break;
            case 2:
                gameLogic.showDevelopersInfo(); // Adiciona os nomes dos desenvolvedores
                break;
            default:
                break;
        }
    }
}
