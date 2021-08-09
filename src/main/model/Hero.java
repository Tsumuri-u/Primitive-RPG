package model;

import exceptions.DoesntExistException;
import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents the player character
public class Hero extends Character implements Writable {
    private final int potionHealAmount = 20;
    private int level;
    private ItemList items;
    private int experience;
    private int expToLevelUp;
    private int storyProgress;

    public Hero(String name) {
        super(100, name);
        this.level = 1;
        this.experience = 0;
        this.expToLevelUp = 100;
        this.attack = 10;
        this.defense = 2;
        items = new ItemList();
        this.storyProgress = 0;
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

    public void setLevel(int x) {
        this.level = x;
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

    public int getStoryProgress() {
        return storyProgress;
    }

    public void setStoryProgress(int x) {
        this.storyProgress = x;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("maxHealth", maxHealth);
        json.put("health", health);
        json.put("attack", attack);
        json.put("defense", defense);
        json.put("level", level);
        json.put("experience", experience);
        json.put("expToLevelUp", expToLevelUp);
        json.put("storyProgress", storyProgress);
        json.put("items", itemsToJson());
        return json;
    }

    // MODIFIES: json
    // EFFECTS: adds the list of items to an array in a json
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : items.getItemList()) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
