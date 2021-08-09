package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import exceptions.FullItemListException;
import model.Hero;
import model.Item;
import org.json.*;

// Represents a reader that reads  Hero from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Hero read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHero(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Hero from JSON object and returns it
    private Hero parseHero(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Hero player = new Hero(name);
        parseStats(player, jsonObject);
        parseItems(player, jsonObject);
        return player;
    }

    // MODIFIES: player
    // EFFECTS: parses stats from JSONObject, adds stats to player
    private void parseStats(Hero player, JSONObject jsonObject) {
        int maxHealth = jsonObject.getInt("maxHealth");
        int health = jsonObject.getInt("health");
        int attack = jsonObject.getInt("attack");
        int defense = jsonObject.getInt("defense");
        int level = jsonObject.getInt("level");
        int experience = jsonObject.getInt("experience");
        int expToLevelUp = jsonObject.getInt("expToLevelUp");
        int storyProgress = jsonObject.getInt("storyProgress");
        player.setMaxHealth(maxHealth);
        player.setHealth(health);
        player.setAttack(attack);
        player.setDefense(defense);
        player.setLevel(level);
        player.setExperience(experience);
        player.setExpToLevelUp(expToLevelUp);
        player.setStoryProgress(storyProgress);
    }

    // MODIFIES: player
    // EFFECTS: parses items from JSONObject, adds items to player's itemlist
    private void parseItems(Hero player,  JSONObject jsonObject) {
        JSONArray jsonArray =  jsonObject.getJSONArray(("items"));
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(player, nextItem);
        }
    }

    // MODIFIES: player
    // EFFECTS: parses item from JSON object and adds it to player's itemlist
    private void addItem(Hero player, JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        if (type.equals("POTION")) {
            try {
                player.addItem(new Item(Item.ItemType.POTION));
            } catch (FullItemListException f) {
                System.out.println("uh oh");
            }
        } else {
            try {
                player.addItem(new Item(Item.ItemType.ELIXIR));
            } catch (FullItemListException f) {
                System.out.println("uh oh");
            }
        }
    }
}
