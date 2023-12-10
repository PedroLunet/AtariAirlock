import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaTest {

    @Test
    public void testCreateWalls() {
        Arena arena = new Arena(100, 50);
        assertNotNull(arena.createWalls());
    }

    @Test
    public void testCreateKeys() {
        Arena arena = new Arena(100, 50);
        assertNotNull(arena.createKeys());
    }

    @Test
    public void testCheckCoinCollision() {
        Arena arena = new Arena(100, 50);

        // Simule uma posição de moeda e do herói para teste
        Position coinPosition = new Position(30, 22);
        Position heroPosition = new Position(30, 22);

        // Crie uma moeda nessa posição simulada
        Coin coin = new Coin(coinPosition.getX(), coinPosition.getY());

        // Adicione a moeda à lista de moedas da arena
        arena.coins.add(coin);

        // Verifique se há colisão entre o herói e a moeda
        assertTrue(arena.checkCoinCollision(heroPosition));
    }

    // Teste para verificar a movimentação dos monstros
    @Test
    public void testMoveMonsters() {
        // Crie uma instância da Arena
        Arena arena = new Arena(100, 50);

        // Crie um mock para um monstro
        Monster mockMonster = mock(Monster.class);

        // Adicione o mock à lista de monstros da arena
        arena.monsters.add(mockMonster);

        // Chame o método para mover os monstros
        arena.moveMonsters();

        // Verifique se o método move do monstro foi chamado
        verify(mockMonster, times(1)).move(arena);
    }
}
