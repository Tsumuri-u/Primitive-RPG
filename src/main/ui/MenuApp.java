package ui;

import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;
import model.Hero;
import model.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// class to handle menus outside of battle/story
public class MenuApp {
    static Scanner input;
    private static Hero player;
    private static final String JSON_STORE = "./data/hero.json";
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private static TitleFrame titleScreen;

    // EFFECTS: initializes scanner, hero, frame, runs the title menu
    public MenuApp() {
        init();
        runTitleMenu();
    }

    // MODIFIES: this
    // EFFECTS: processes player input
    private void runTitleMenu() {
        String command = null;
        System.out.println("Welcome to the game!");

        while (true) {
            displayTitleMenu();
            command = input.next();
            processTitleCommand(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes player commands on the title screen
    private void processTitleCommand(String command) {
        switch (command) {
            case "1":
                startGame();
                break;
            case "2":
                loadGame();
            case "3":
                System.out.println("Goodbye.");
                System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes panel, frame, scanner, hero, json reader and json writer
    private void init() {
        input = new Scanner(System.in);
        player = new Hero("");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        titleScreen  = new TitleFrame();
    }

    // EFFECTS: displays a list of options to the player upon opening the game
    private static void displayTitleMenu() {
        titleScreen.drawTitleMenu();
        System.out.println("\n(1) -> start game");
        System.out.println("\n(2) -> load game");
        System.out.println("\n(3) -> quit game");
    }

    // MODIFIES: this
    // EFFECTS: prints the beginning of the game, sets Hero's name
    public static void startGame() {
        titleScreen.drawStartGameMenu();
//        String name;
//        System.out.println("Welcome, hero."
//                + "\nPlease enter your name.");
//        name = input.next();
//        player.setName(name);
//        System.out.println("Welcome, " + player.getName());
//        player.setStoryProgress(0);
//        new Story(player);
    }

    // MODIFIES: this
    // EFFECTS: loads hero from file, enters point in story corresponding to storyProgress
    public static void loadGame() {
        try {
            player = jsonReader.read();
            titleScreen.drawSuccessfulLoadGameMenu();
//            System.out.println("Loaded " + player.getName() + " from " + JSON_STORE);
//            pressToContinue();
//            new Story(player);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays options to add or remove items
    public static void displayItemMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\n(1) Add items");
        System.out.println("\n(2) Remove items");
    }

    // MODIFIES: this
    // EFFECTS: ADDS items to player item list (items: Potion, Elixir)
    public static void equipItems(String command) {
        if (command.equals("1")) {
            try {
                player.addItem(new Item(Item.ItemType.POTION));
                titleScreen.displaySuccessAddItem();
            } catch (FullItemListException f) {
                titleScreen.displayFullItemScreen();
            }
        } else if (command.equals("2")) {
            try {
                player.addItem(new Item(Item.ItemType.ELIXIR));
                titleScreen.displaySuccessAddItem();
            } catch (FullItemListException f) {
                titleScreen.displayFullItemScreen();
            }
        }
    }

    // EFFECTS: displays list of possible items to equip (items: Potion, Elixir)
    public static void displayItemOptions() {
        titleScreen.displayEquipPrompt();
//        System.out.println("\nWhat would you like to equip?");
//        System.out.println("\n(1) Potion");
//        System.out.println("\n(2) Elixir");
    }

    // MODIFIES: this
    // EFFECTS: REMOVES items from player item list
    public static void removeItems(String command) {
//        String command = null;
//        command =  input.next();
//        System.out.println("\nWhich item would you like to remove?");
//        System.out.println("\nCurrently equipped items:");
//        System.out.println(player.getItems().listOutItems());
        try {
            player.removeItem(Integer.parseInt(command) - 1);
            titleScreen.displaySuccessRemoveItem();
            System.out.println("\nSuccessfully removed item.");
        } catch (TooBigNumberException e) {
            System.out.println("That number is too big!");
//            removeItems();
        } catch (EmptyListException e) {
            titleScreen.displayNoItemsScreen();
            System.out.println("\nYour items list is already empty!");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays gui to remove items
    public static void displayRemoveItems() {
        titleScreen.displayRemovePrompt();
    }

    // EFFECTS: prompts use to enter input to continue
    public static void pressToContinue() {
        System.out.println("---------------------------");
        System.out.println("Enter anything to continue.");
        input.next();
    }

    // MODIFIES: this
    // EFFECTS: opens a menu that allows player to continue journey, view stats, equip items, rest, save, and quit game
    public static void middleMenu() {
        String command = null;
        displayManageMenu();
//        command = input.next();
//        processMiddleMenuCommand(command);
    }

    // EFFECTS: displays options to the player to manage the hero character
    public static void displayManageMenu() {
        titleScreen = new TitleFrame();
        titleScreen.displayMiddleMenu();
    }

    // MODIFIES: this
    // EFFECTS: processes the commands for middle menu
    public static void processMiddleMenuCommand(String command) {
        switch (command) {
            case "1":
                break;
            case "2":
                displayStats();
                break;
            case "3":
                restCharacter();
                break;
            case "4":
                manageItems();
                break;
            case "5":
                saveGame();
                break;
            case "6":
                System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: lets user manage items such as potions
    public static void manageItems() {
        titleScreen.displayItemsPrompt();
//        String command = null;
////        System.out.println("\nCurrently equipped items: ");
////        System.out.println(player.getItems().listOutItems());
//        displayItemMenu();
////        command =  input.next();
//        if (command.equals("1")) {
//            equipItems();
//        } else if (command.equals("2")) {
//            removeItems();
//        }
    }

    // MODIFIES: this
    // EFFECTS: displays the current stats of the character
    public static void displayStats() {
        titleScreen.displayStats();
    }

    // MODIFIES: this
    // EFFECTS: heals character to full
    public static void restCharacter() {
        player.setHealth(player.getMaxHealth());
        titleScreen.displayRestMenu();
    }

    // MODIFIES: this
    // EFFECTS: lets the player save the game
    public static void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(player);
            jsonWriter.close();
            System.out.println("Saved " + player.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: returns player
    public static Hero getPlayer() {
        return player;
    }
}