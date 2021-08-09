package model;

// Represents a character in-game
public abstract class Character {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int attack;
    protected int defense;

    public Character(int maxHealth, String name) {
        this.maxHealth = maxHealth;
        this.name = name;
        this.health = maxHealth;
    }

    // =======================================
    // GETTERS AND SETTERS
    // =======================================
    public String getName() {
        return this.name;
    }

    public void setName(String x) {
        this.name = x;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int x) {
        this.maxHealth = x;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int x) {
        this.health = x;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int x) {
        this.attack = x;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int x) {
        this.defense = x;
    }

    // EFFECTS: returns an int with attack value
    public abstract int attack();

    // EFFECTS: returns an int with defense value
    public abstract int defend();



}
