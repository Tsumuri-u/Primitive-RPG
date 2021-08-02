package model;

import exceptions.DoesntExistException;
import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;

public class Hero extends Character {
    private final int potionHealAmount = 20;
    private int level;
    private ItemList items;
    private int experience;
    private int expToLevelUp;

    public Hero(String name) {
        super(100, name);
        this.level = 1;
        this.experience = 0;
        this.expToLevelUp = 100;
        this.attack = 10;
        this.defense = 2;
        items = new ItemList();
    }

    // MODIFIES: this
    // EFFECTS: increases character's statistics
    public void levelUp() {
        System.out.println(this.name + " levelled up! Max HP, Attack and Defense has increased!");
        this.level += 1;
        this.maxHealth *= 1.1;
        this.attack += level;
        this.defense += 1;
        this.expToLevelUp += (int) (100 * (1.5 * (level - 1)));
    }

    // =======================================
    // GETTERS AND SETTERS
    // =======================================
    public ItemList getItems() {
        return this.items;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExpToLevelUp() {
        return expToLevelUp;
    }

    public void setExpToLevelUp(int expToLevelUp) {
        this.expToLevelUp = expToLevelUp;
    }

    // MODIFIES: this
    // EFFECTS: adds item to the hero's list of items
    public void addItem(Item item) throws FullItemListException {
        this.items.addItem(item);
    }

    // MODIFIES: this
    // EFFECTS: removes item at index x from items
    public void removeItem(int x) throws TooBigNumberException, EmptyListException {
        this.items.removeItem(x);
    }

    // MODIFIES: this
    // EFFECTS: heals potionHealAmount and removes potion from itemlist
    public void usePotion() throws EmptyListException, DoesntExistException {
        this.items.removeItem(Item.ItemType.POTION);
        this.health += potionHealAmount;
        if (this.health >= this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    @Override
    public int attack() {
        return (int) attack;
    }

    @Override
    public int defend() {
        return (int) defense;
    }
}
