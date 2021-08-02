package model;

// Represents a consumable item that the player can use
public class Item {
    public enum ItemType { POTION, ELIXIR }

    private ItemType type;

    public Item(ItemType type) {
        this.type = type;
    }

    public ItemType getType() {
        return this.type;
    }


}
