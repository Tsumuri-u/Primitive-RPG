package ui;

import exceptions.DoesntExistException;
import exceptions.EmptyListException;
import model.Enemy;
import model.Hero;

import java.util.Scanner;

// Class to handle battles, including their menus and logic
public class BattleMenu {
    private Scanner input;
    private Hero player;
    private Enemy enemy;

    // Instantiates a new battle with player and an enemy
    public BattleMenu(Hero player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        input = new Scanner(System.in);
        battle();
    }

    // MODIFIES: this, enemy
    // EFFECTS: serves as the main battle loop where player can choose actions
    public void battle() {
        String command = null;
        boolean stillAlive = enemy.stillAlive();
        while (stillAlive) {
            displayHealth();
            displayBattleMenu();
            command = input.next();
            processBattleCommand(command);
            stillAlive = enemy.stillAlive();
        }
    }

    // EFFECTS: displays the health of the enemy and the player
    public void displayHealth() {
        System.out.println(enemy.getName() + "\nHP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        System.out.println(player.getName() + "\nHP: " + player.getHealth() + "/" + player.getMaxHealth());
    }

    // EFFECTS: displays options in the battle menu
    public void displayBattleMenu() {
        System.out.println("Choose an action:");
        System.out.println("\n(1) Attack\n(2) Use an item");
    }

    // MODIFIES: this, enemy
    // EFFECTS: processes the action chosen in battle
    public void processBattleCommand(String command) {
        switch (command) {
            case "1":
                int dmgGiven = player.attack() - enemy.defend();
                int dmgTaken = enemy.attack() - player.defend();
                if (dmgGiven < 0) {
                    dmgGiven = 1;
                }
                if (dmgTaken < 0) {
                    dmgTaken = 1;
                }
                processAttack(dmgGiven, dmgTaken);
                displayDamageInfo(dmgGiven, dmgTaken);
                if (player.getHealth() <= 0) {
                    gameOver();
                } else if (enemy.getHealth() <= 0) {
                    enemyDefeated();
                }
                break;
            case "2":
                battleItemMenu();
                break;
        }
    }

    // EFFECTS: sets health of player and enemy
    public void processAttack(int dmgGiven, int dmgTaken) {
        player.setHealth(player.getHealth() - dmgTaken);
        enemy.setHealth(enemy.getHealth() - dmgGiven);
    }

    // EFFECTS: displays damage information after an attack
    public void displayDamageInfo(int dmgGiven, int dmgTaken) {
        System.out.println("You dealt " + dmgGiven + " damage to the " + enemy.getName());
        System.out.println(enemy.getName() + " dealt " + dmgTaken + " to you");
    }

    // EFFECTS: displays game over screen
    public void gameOver() {
        System.out.println(player.getName() + " has perished.");
        System.out.println("Better luck next time.");
        TitleMenuApp.pressToContinue();
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: script for when enemy is defeated
    public void enemyDefeated() {
        System.out.println("You defeated the " + enemy.getName() + "!");
        player.setExperience(player.getExperience() +  enemy.getExpGiven());
        if (player.getExperience() >= player.getExpToLevelUp()) {
            player.levelUp();
        }
        TitleMenuApp.pressToContinue();
    }

    // EFFECTS: menu for using items in battle
    public void battleItemMenu() {
        String command = null;
        displayItemMenuText();
        command = input.next();
        if (command.equals("1")) {
            try {
                player.usePotion();
                enemy.powerUp(player.getLevel());
                System.out.println("Restored HP!");
                System.out.println("The enemy powered up while you used your potion!");
            } catch (EmptyListException e) {
                System.out.println("You don't have any items!");
                enemy.powerUp(player.getLevel());
                System.out.println("The enemy powered up while you wasted your time!");
            } catch (DoesntExistException d) {
                System.out.println("You don't have any potions!");
                enemy.powerUp(player.getLevel());
                System.out.println("The enemy powered up while you wasted your time!");
            }
        } else {
            enemy.powerUp(1);
            System.out.println("The enemy powered up while you wasted your time!");
        }
    }

    // EFFECTS: displays text options for item menu
    public void displayItemMenuText() {
        System.out.println("What item would you like to use?");
        System.out.println("(1) Potion");
    }
}
