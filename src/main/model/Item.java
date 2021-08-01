package model;

// Represents a consumable item that the player can use
public class Item {
    private String type;
    private int value;

    public Item(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public int getValue() {
        return value;
    }

}
