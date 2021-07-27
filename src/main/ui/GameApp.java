package ui;

import model.Hero;

import java.util.Scanner;

// Code inspired by TellerApp
// RPG game app
public class GameApp {
    private Scanner input;
    private Hero player;

    // EFFECTS: runs the game
    public GameApp() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes player input
    private void runGame() {
        String command = null;
        System.out.println("Welcome to the game!");
        init();

        displayTitleMenu();
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("3")) {
            System.exit(0);
            System.out.println("Goodbye.");
        } else {
            processCommand(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes player commands
    private void processCommand(String command) {
        if (command.equals("1")) {
            startGame();
        } else if (command.equals("2")) {
            manageCharacter();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes objects
    private void init() {
        input = new Scanner(System.in);
    }

    // EFFECTS: displays a list of options to the player upon opening the game
    private void displayTitleMenu() {
        System.out.println("\n(1) -> start game");
        System.out.println("\n(2) -> manage character");
        System.out.println("\n(3) -> quit game");
    }

    // MODIFIES: this
    // EFFECTS: Prints the beginning of the game, sets Hero's name
    public void startGame() {
        String name;
        System.out.println("Welcome, hero."
                + "\nPlease enter your name.");
        name = input.next();
        player = new Hero(name);
        System.out.println("Welcome, " + player.getName());
    }

    // MODIFIES: this
    // EFFECTS: lets user rest (to regain health) and equip items (to use in battle)
    public void manageCharacter() {
        displayManageMenu();
    }

    // EFFECTS: displays options to the player to manage the hero character
    public void displayManageMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\n(1) Rest to regain HP");
        System.out.println("\n(2) Equip items");
        System.out.println("\n(3) Return to the menu");
    }

}
