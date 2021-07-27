package model;

// Represents an enemy that the player can fight
public class Enemy extends Character {

    public Enemy(String name) {
        super(10, 0, name);
    }

    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int defend() {
        return 0;
    }
}
