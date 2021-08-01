package ui;

import model.Hero;

// Class that contains the story scripts for the game
public class Story {
    private Hero player;

    // Instatiates the story with the player character
    public Story(Hero player) {
        this.player = player;
        startStory();
    }

    // EFFECTS: continues where the story left off
    // TODO: implement
    public void startStory() {
        if (player.getLevel() == 1 && player.getExpToLevelUp() == 100) {
            intro();
        }
    }

    // EFFECTS: prints the intro to the story
    public void intro() {
        System.out.println(player.getName() + ", the world is in trouble, it is up to you to save it!");
        System.out.println("Make your way to the demon king and defeat him!");
    }
}
