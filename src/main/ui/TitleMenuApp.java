package ui;

import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;
import model.Hero;
import model.Item;

import java.util.Scanner;

// RPG game app
public class TitleMenuApp {
    private Scanner input;
    private Hero player;

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
    private void processTitleCommand(String command) {
        switch (command) {
            case "1":
                startGame();
                break;
            case "2":
                manageCharacter();
                break;
            case "3":
                System.exit(0);
                System.out.println("Goodbye.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner, hero
    private void init() {
        input = new Scanner(System.in);
        player = new Hero("");
    }

    // EFFECTS: displays a list of options to the player upon opening the game
    // TODO: change "start game" to "continue game" if a save exists
    private void displayTitleMenu() {
        System.out.println("\n(1) -> start game");
        System.out.println("\n(2) -> manage character");
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

    // MODIFIES: this
    // EFFECTS: lets user view stats, rest (to regain health) and equip items (to use in battle)
    // TODO: show player's stat info
    public void manageCharacter() {
        String command = null;
        displayManageMenu();
        command = input.next();

        if (command.equals("1")) {
            player.setHealth(player.getMaxHealth());
            System.out.println("Health restored to max!");
        } else if (command.equals("2")) {
            manageItems();
        }
    }

    // EFFECTS: displays options to the player to manage the hero character
    public void displayManageMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\n(1) Rest to regain HP");
        System.out.println("\n(2) Equip items");
        System.out.println("\n(3) Return to the menu");
    }

    // MODIFIES: this
    // EFFECTS: lets user manage items such as potions
    public void manageItems() {
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
    }

    // EFFECTS: displays options to add or remove items
    public void displayItemMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\n(1) Add items");
        System.out.println("\n(2) Remove items");
    }

    // MODIFIES: this
    // EFFECTS: ADDS items to player item list (items: Potion, Elixir)
    public void equipItems() {
        String command = null;
        displayItemOptions();
        command =  input.next();
        if (command.equals("1")) {
            try {
                player.addItem(new Item("Potion", 10));
            } catch (FullItemListException f) {
                System.out.println("Your items list is full!");
            }
        } else if (command.equals("2")) {
            try {
                player.addItem(new Item("Elixir", 20));
            } catch (FullItemListException f) {
                System.out.println("Your items list is full!");
            }
        }
    }

    // EFFECTS: displays list of possible items to equip (items: Potion, Elixir)
    public void displayItemOptions() {
        System.out.println("\nWhat would you like to equip?");
        System.out.println("\n(1) Potion");
        System.out.println("\n(2) Elixir");
    }

    // MODIFIES: this
    // EFFECTS: REMOVES items from player item list
    public void removeItems() {
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

}