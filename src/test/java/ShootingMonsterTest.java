import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class ShootingMonsterTest {

    @Test
    public void testMove() {
        Arena arena = new Arena(100, 28);
        SpeedMonster monster = new SpeedMonster(25, 24);
        arena.monsters.add(monster);
        monster.move(arena);
        Assertions.assertEquals(25, monster.getPosition().getX());
    }
    @Test
    public void testAction_ShouldNotShootIfHeroNotInSameRow() {
        Arena arena = new Arena(10, 10);
        Hero hero = new Hero(5, 5);
        ShootingMonster shootingMonster = new ShootingMonster(3, 3);

        shootingMonster.action(arena, hero);

        Assertions.assertEquals(0, arena.bullets.size());
    }

    @Test
    public void testAction_ShouldShootIfHeroInSameRow() {
        Arena arena = new Arena(10, 10);
        Hero hero = new Hero(5, 3);
        ShootingMonster shootingMonster = new ShootingMonster(3, 3);

        shootingMonster.action(arena, hero);

        Assertions.assertEquals(1, arena.bullets.size());
    }
}
