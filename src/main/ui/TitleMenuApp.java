package ui;

import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;
import model.Hero;
import model.Item;

import java.util.Scanner;

// RPG game app
public class TitleMenuApp {
    static Scanner input;
    private static Hero player;

    // EFFECTS: initializes scanner, hero, runs the title menu
    public TitleMenuApp() {
        init();
        runTitleMenu();
    }

    // MODIFIES: this
    // EFFECTS: processes player input
    private void runTitleMenu() {
        boolean keepRunning = true;
        String command = null;
        System.out.println("Welcome to the game!");

        while (keepRunning) {
            displayTitleMenu();
            command = input.next();
            processTitleCommand(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes player commands on the title screen
    // TODO: implement command for loading game
    private void processTitleCommand(String command) {
        switch (command) {
            case "1":
                startGame();
                break;
            case "2":

            case "3":
                System.out.println("Goodbye.");
                System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner, hero
    private void init() {
        input = new Scanner(System.in);
        player = new Hero("");
    }

    // EFFECTS: displays a list of options to the player upon opening the game
    private void displayTitleMenu() {
        System.out.println("\n(1) -> start game");
        System.out.println("\n(2) -> load game");
        System.out.println("\n(3) -> quit game");
    }

    // MODIFIES: this
    // EFFECTS: If no save exists, prints the beginning of the game, sets Hero's name
    // TODO: do something else if a save exists
    public void startGame() {
        String name;
        System.out.println("Welcome, hero."
                + "\nPlease enter your name.");
        name = input.next();
        player.setName(name);
        System.out.println("Welcome, " + player.getName());
        new Story(player);
    }

    // EFFECTS: displays options to add or remove items
    public static void displayItemMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\n(1) Add items");
        System.out.println("\n(2) Remove items");
    }

    // MODIFIES: this
    // EFFECTS: ADDS items to player item list (items: Potion, Elixir)
    public static void equipItems() {
        String command = null;
        displayItemOptions();
        command =  input.next();
        if (command.equals("1")) {
            try {
                player.addItem(new Item(Item.ItemType.POTION));
            } catch (FullItemListException f) {
                System.out.println("Your items list is full!");
            }
        } else if (command.equals("2")) {
            try {
                player.addItem(new Item(Item.ItemType.ELIXIR));
            } catch (FullItemListException f) {
                System.out.println("Your items list is full!");
            }
        }
    }

    // EFFECTS: displays list of possible items to equip (items: Potion, Elixir)
    public static void displayItemOptions() {
        System.out.println("\nWhat would you like to equip?");
        System.out.println("\n(1) Potion");
        System.out.println("\n(2) Elixir");
    }

    // MODIFIES: this
    // EFFECTS: REMOVES items from player item list
    public static void removeItems() {
        String command = null;
        command =  input.next();
        System.out.println("\nWhich item would you like to remove?");
        System.out.println("\nCurrently equipped items:");
        System.out.println(player.getItems().listOutItems());
        try {
            player.removeItem(Integer.parseInt(command) - 1);
            System.out.println("\nSuccessfully removed item.");
        } catch (TooBigNumberException e) {
            System.out.println("That number is too big!");
            removeItems();
        } catch (EmptyListException e) {
            System.out.println("\nYour items list is already empty!");
        }
    }

    // EFFECTS: prompts use to enter input to continue
    public static void pressToContinue() {
        System.out.println("Enter anything to continue.");
        input.next();
    }

    // MODIFIES: this
    // EFFECTS: opens a menu that allows player to continue journey, view stats, equip items, rest, save, and quit game
    // TODO: implement this
    public static void middleMenu() {
        String command = null;
        displayManageMenu();
        command = input.next();
        processMiddleMenuCommand(command);
    }

    // EFFECTS: displays options to the player to manage the hero character
    public static void displayManageMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\n(1) Continue Journey");
        System.out.println("\n(2) View character stats");
        System.out.println("\n(3) Rest to regain HP");
        System.out.println("\n(4) Manage items");
        System.out.println("\n(5) Save game");
        System.out.println("\n(6) Quit game");
    }

    // MODIFIES: this
    // EFFECTS: processes the commands for middle menu
    // TODO: implement this
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
                System.out.println("Goodbye.");
                System.exit(0);
            default:
                System.out.println("Invalid command");
                middleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: lets user manage items such as potions
    public static void manageItems() {
        String command = null;
        System.out.println("\nCurrently equipped items: ");
        System.out.println(player.getItems().listOutItems());
        displayItemMenu();
        command =  input.next();
        if (command.equals("1")) {
            equipItems();
        } else if (command.equals("2")) {
            removeItems();
        }
        middleMenu();
    }

    // MODIFIES: this
    // EFFECTS: displays the current stats of the character
    public static void displayStats() {
        System.out.println("Your current stats:");
        System.out.println("Level: " + player.getLevel());
        System.out.println("Current HP: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("Experience: " + player.getExperience());
        System.out.println("XP needed for next level: " + player.getExpToLevelUp());
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Equipped items: " + player.getItems().listOutItems());
        pressToContinue();
        middleMenu();
    }

    // MODIFIES: this
    // EFFECTS: heals character to full
    public static void restCharacter() {
        player.setHealth(player.getMaxHealth());
        System.out.println("Health restored to max!");
        middleMenu();
    }

    // MODIFIES: this
    // EFFECTS: lets the player save the game
    // TODO: implement this
    public static void saveGame() {

    }
}