package model;

// Represents a character in-game
public abstract class Character {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int attack;
    protected int defense;
    protected int experience;
    protected int expToLevelUp;

    public Character(int maxHealth, int experience, String name) {
        this.maxHealth = maxHealth;
        this.experience = experience;
        this.name = name;
        this.health = maxHealth;
    }

    public String getName() {
        return  this.name;
    }

    // EFFECTS: returns an int with attack value
    public abstract int attack();

    // EFFECTS: returns an int with defense value
    public abstract int defend();



}
