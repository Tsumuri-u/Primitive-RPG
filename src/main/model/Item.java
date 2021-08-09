package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a consumable item that the player can use
public class Item implements Writable {
    public enum ItemType { POTION, ELIXIR }

    private ItemType type;

    public Item(ItemType type) {
        this.type = type;
    }

    public ItemType getType() {
        return this.type;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        return json;
    }

}
