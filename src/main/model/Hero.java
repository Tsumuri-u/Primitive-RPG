package model;

public class Hero extends Character {
    private int level;
    private Item[] items  = {};

    public Hero(String name) {
        super(100, 0, name);
        this.level = 1;
    }

    // MODIFIES: this
    // EFFECTS: increases character's statistics
    public void levelUp() {
        System.out.println(this.name + " levelled up! Max HP, Attack and Defense has increased!");
        this.level += 1;
        this.maxHealth *= 1.1;
        this.attack  *= 1.1;
        this.defense *= 1.1;
        this.experience = 0;
        this.expToLevelUp *= 1.5;
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
