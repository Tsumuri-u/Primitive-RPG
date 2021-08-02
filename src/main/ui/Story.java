package ui;

import model.Enemy;
import model.Hero;

// Class that contains the story scripts for the game
public class Story {
    private Hero player;

    // Instantiates the story with the player character
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
        System.out.println("Make your way to the Demon King and defeat him!");
        TitleMenuApp.pressToContinue();
        outsideOne();
    }

    // EFFECTS: prints the first part of the "Outside" arc, where a slime attacks
    public void outsideOne() {
        System.out.println("You embark on your journey toward the Demon King's castle.");
        System.out.println("You walk the grassy fields outside the kingdom.");
        System.out.println("Suddenly, a slime appears!");
        TitleMenuApp.pressToContinue();
        new BattleMenu(player, new Enemy(30, "Slime", 150, 5, 1));
        TitleMenuApp.middleMenu();
        outsideTwo();
    }

    // EFFECTS: prints the second part of the "Outside" arc, where a goblin attacks
    public void outsideTwo() {
        System.out.println("After defeating the slime, you continue your journey.");
        System.out.println("Your journey leads you to a forest.");
        System.out.println("Suddenly, a goblin jumps out of a bush and attacks!");
        TitleMenuApp.pressToContinue();
        new BattleMenu(player, new Enemy(50, "Goblin", 250, 7, 3));
        TitleMenuApp.middleMenu();
        outsideThree();
    }

    // EFFECTS: prints the third part of the "Outside" arc, where a skeleton attacks
    public void outsideThree() {
        System.out.println("After defeating the goblin, you continue your journey.");
        System.out.println("Night falls and the air gets very misty.");
        System.out.println("Out of the mist, a skeleton appears and attacks!");
        new BattleMenu(player, new Enemy(60, "Skeleton", 400, 10, 5));
        TitleMenuApp.middleMenu();
        outsideFour();
    }

    // EFFECTS: prints the fourth part of the "Outside" arc, where a giant worm attacks
    public void outsideFour() {
        System.out.println("After defeating the skeleton, you continue your journey.");
        System.out.println("Day breaks and you begin making your way through a sandy desert.");
        System.out.println("Suddenly, a giant worm appears out of the ground and attacks!");
        new BattleMenu(player, new Enemy(70, "Giant Worm", 800, 13, 8));
        TitleMenuApp.middleMenu();
        towerOne();
    }

    // EFFECTS: prints the first part of the "Tower" arc, where a giant spider attacks
    public void towerOne() {
        System.out.println("After defeating the giant worm, you continue your journey.");
        System.out.println("You come across a tower in the middle of the desert.");
        System.out.println("You enter the tower and make your way to the top.");
        System.out.println("Suddenly, a giant spider quickly crawls its way toward you!");
        new BattleMenu(player, new Enemy(80, "Giant Spider", 1000, 15, 9));
        TitleMenuApp.middleMenu();
        towerTwo();
    }

    // EFFECTS: prints the second part of the "Tower" arc, where a lich attacks
    public void towerTwo() {
        System.out.println("After defeating the lich, you continue up the tower");
        System.out.println("You find yourself in a large dilapidated dungeon.");
        System.out.println("From the corner, a minotaur attacks!");
        new BattleMenu(player, new Enemy(90, "Lich", 1300, 17, 10));
        TitleMenuApp.middleMenu();
        towerThree();
    }

    // EFFECTS: prints the third part of the "Tower" arc, where a minotaur attacks
    public void towerThree() {
        System.out.println("After defeating the lich, you continue up the tower");
        System.out.println("You find yourself in a large dilapidated dungeon.");
        System.out.println("From the corner, a minotaur attacks!");
        new BattleMenu(player, new Enemy(100, "Minotaur", 1600, 19, 12));
        TitleMenuApp.middleMenu();
        lairOne();
    }

    // EFFECTS: prints the first part of the "Lair" arc, where a dragon attacks
    public void lairOne() {
        System.out.println("After defeating the minotaur, you continue up the tower.");
        System.out.println("You reach the top of the tower, and are met with the castle of the Demon King.");
        System.out.println("You enter his lair, making your way toward the king's chamber.");
        System.out.println("A dragon guards the path ahead.");
        new BattleMenu(player, new Enemy(120, "Dragon", 2000, 20, 14));
        TitleMenuApp.middleMenu();
        lairTwo();
    }

    // EFFECTS: prints the second part of the "Lair" arc, where the Demon King attacks
    public void lairTwo() {
        System.out.println("After defeating the dragon, you make your way through the castle.");
        System.out.println("You enter the throne room.");
        System.out.println("Finally, you come face to face with the Demon King.");
        System.out.println("\"This is where your journey ends, hero\"");
        System.out.println("Defeat the Demon King!");
        new BattleMenu(player, new Enemy(150, "Demon King", 3000, 22, 15));
        finale();
    }

    // EFFECTS: prints the finale
    public void finale() {
        System.out.println("The Demon King has been defeated!");
        System.out.println("The kingdom has been saved!");
        System.out.println("Congratulations, hero!");
        TitleMenuApp.pressToContinue();
        System.exit(0);
    }
}
