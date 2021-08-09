package model;

// Represents an enemy that the player can fight
public class Enemy extends Character {
    private int expGiven;

    public Enemy(int maxhealth, String name, int expGiven, int attack, int defense) {
        super(maxhealth, name);
        this.expGiven = expGiven;
        this.attack = attack;
        this.defense = defense;
    }

    public int getExpGiven() {
        return expGiven;
    }

    @Override
    public int attack() {
        return (int) attack;
    }

    @Override
    public int defend() {
        return (int) defense;
    }

    // MODIFIES: this
    // EFFECTS: increases attack value by x
    public void powerUp(int  x) {
        this.attack += x;
    }

    // EFFECTS: returns true if enemy is still alive, false otherwise
    public boolean stillAlive() {
        return (this.getHealth() > 0);
    }
}
