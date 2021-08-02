package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void runBefore() {
        enemy = new Enemy(10, "Slime", 50, 2, 1);
    }

    @Test
    void testStillAliveTrue() {
        assertTrue(enemy.stillAlive());
    }

    @Test
    void testStillAliveFalse() {
        enemy.setHealth(0);
        assertFalse(enemy.stillAlive());
        enemy.setHealth(-4);
        assertFalse(enemy.stillAlive());
    }

    // TODO: fix this shit
    @Test
    void testAttack() {
        assertEquals(2, enemy.attack());
    }

    // TODO: fix this shit
    @Test
    void testDefense() {
        assertEquals(1, enemy.defend());
    }
}
