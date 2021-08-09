package ui;

import exceptions.DoesntExistException;
import exceptions.EmptyListException;
import model.Enemy;
import model.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

// Class to handle battles, including their menus and logic
public class BattleMenu extends JFrame implements ActionListener {
    private Scanner input;
    private Hero player;
    private Enemy enemy;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 700;
    private static JPanel panel;
    private static JLabel playerHealth;
    private static JLabel enemyHealth;
    private static JLabel currentMessage;
    private static JLabel healIndicator;
    private static JLabel enemyDamageIndicator;
    private static JLabel heroDamageIndicator;
    private static JLabel heroLabel;
    private static JLabel enemyLabel;
    private static JButton attack;
    private static JButton useItem;
    private static JButton potion;
    private static JButton proceed;
    private static final ImageIcon icon = new ImageIcon("./data/title_icon.png");
    private static ImageIcon enemySprite;
    private static ImageIcon heroSprite = new ImageIcon("./data/hero.png");

    // Instantiates a new battle with player and an enemy, along with indicators
    public BattleMenu(Hero player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        initializeGraphics();
        setEnemySprite();
        currentMessage = new JLabel();
        currentMessage.setBounds(100, 50, 1024, 100);
        currentMessage.setForeground(new Color(0x7B0925));
        currentMessage.setFont(new Font("Helvetica", Font.PLAIN, 20));
        healIndicator = new JLabel();
        healIndicator.setBounds(WIDTH - 128, HEIGHT / 2 - 256, 1024, 110);
        healIndicator.setForeground(new Color(0x50D584));
        healIndicator.setFont(new Font("Helvetica", Font.PLAIN, 20));
        enemyDamageIndicator = new JLabel();
        enemyDamageIndicator.setBounds(128, HEIGHT / 2 - 256, 1024, 150);
        enemyDamageIndicator.setForeground(new Color(0x7B0925));
        enemyDamageIndicator.setFont(new Font("Helvetica", Font.PLAIN, 20));
        heroDamageIndicator = new JLabel();
        heroDamageIndicator.setBounds(WIDTH - 128, HEIGHT / 2 - 256, 1024, 150);
        heroDamageIndicator.setForeground(new Color(0x7B0925));
        heroDamageIndicator.setFont(new Font("Helvetica", Font.PLAIN, 20));
        battle();
    }

    // MODIFIES: this, enemy
    // EFFECTS: serves as the main battle loop where player can choose actions
    public void battle() {
        boolean stillAlive = enemy.stillAlive();
        if (stillAlive) {
            displayHud();
            displayBattleMenu();
            stillAlive = enemy.stillAlive();
        } else if (!stillAlive) {
            enemyDefeated();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the health of the enemy and the player, along with characters, messages and indicators
    public void displayHud() {
        clearPanel();
        displayCharacters();
        enemyHealth = new JLabel(enemy.getName() + " HP: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        enemyHealth.setBounds(400, HEIGHT / 2 - 200, 1024, 100);
        enemyHealth.setFont(new Font("Helvetica", Font.BOLD, 30));
        playerHealth = new JLabel(player.getName() + " HP: " + player.getHealth() + "/" + player.getMaxHealth());
        playerHealth.setBounds(400, HEIGHT / 2 - 150, 1024, 100);
        playerHealth.setFont(new Font("Helvetica", Font.BOLD, 30));
        panel.add(enemyHealth);
        panel.add(playerHealth);
        panel.add(currentMessage);
        panel.add(healIndicator);
        panel.add(enemyDamageIndicator);
        panel.add(heroDamageIndicator);
    }

    // MODIFIES: this
    // EFFECTS: displays options in the battle menu
    public void displayBattleMenu() {
        attack = new JButton("Attack");
        attack.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 50, 200, 50);
        useItem = new JButton("Use Item");
        useItem.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(attack);
        attack.addActionListener(this);
        panel.add(useItem);
        useItem.addActionListener(this);
    }

    // MODIFIES: this, enemy
    // EFFECTS: processes the action chosen in battle
    public void processBattleCommand(String command) {
        switch (command) {
            case "1":
                currentMessage.setText("");
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
                } else {
                    battle();
                }
                break;
            case "2":
                displayItemMenuText();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets health of player and enemy
    public void processAttack(int dmgGiven, int dmgTaken) {
        player.setHealth(player.getHealth() - dmgTaken);
        enemy.setHealth(enemy.getHealth() - dmgGiven);
    }

    // MODIFIES: this
    // EFFECTS: displays damage information after an attack
    public void displayDamageInfo(int dmgGiven, int dmgTaken) {
        enemyDamageIndicator.setText(Integer.toString(dmgGiven));
        heroDamageIndicator.setText(Integer.toString(dmgTaken));
    }

    // MODIFIES: this
    // EFFECTS: displays game over screen
    public void gameOver() {
        clearPanel();
        JLabel perished = new JLabel(player.getName() + " has perished.");
        perished.setForeground(new Color(0x7B0925));
        perished.setFont(new Font("Helvetica", Font.PLAIN, 20));
        perished.setBounds(WIDTH / 2 - 100, HEIGHT / 2, 200, 50);
        JLabel nextTime = new JLabel("Better luck next time.");
        nextTime.setForeground(new Color(0x7B0925));
        nextTime.setFont(new Font("Helvetica", Font.PLAIN, 20));
        nextTime.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 50, 250, 50);
        JButton endGame = new JButton("End");
        endGame.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        endGame.addActionListener(e -> System.exit(0));
        panel.add(perished);
        panel.add(nextTime);
        panel.add(endGame);
    }

    // MODIFIES: this
    // EFFECTS: script for when enemy is defeated
    public void enemyDefeated() {
        clearPanel();
        int offset = 0;
        JLabel defeated = new JLabel("You defeated the " + enemy.getName() + "!");
        defeated.setForeground(new Color(0x50D584));
        defeated.setFont(new Font("Helvetica", Font.PLAIN, 20));
        defeated.setBounds(WIDTH / 2 - 200, HEIGHT / 2 -  100, 500, 50);
        panel.add(defeated);
        player.setStoryProgress(player.getStoryProgress() + 1);
        player.setExperience(player.getExperience() +  enemy.getExpGiven());
        while (player.getExperience() >= player.getExpToLevelUp()) {
            player.levelUp();
            JLabel levelUp = new JLabel("Levelled up to " + player.getLevel() + "!");
            levelUp.setBounds(WIDTH / 2 - 100, HEIGHT / 2 - 50  + offset, 200, 50);
            levelUp.setFont(new Font("Helvetica", Font.PLAIN, 20));
            levelUp.setForeground(new Color(0x50D584));
            panel.add(levelUp);
            offset += 25;
        }
        proceed = new JButton("Proceed");
        proceed.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        proceed.addActionListener(this);
        panel.add(proceed);
    }

    // MODIFIES: this
    // EFFECTS: processes command for using item
    public void processItemCommand(String command) {
        if (command.equals("1")) {
            try {
                player.usePotion();
                enemy.powerUp(player.getLevel());
                currentMessage.setText("The enemy powered up while you used your potion!");
                healIndicator.setText("20");
            } catch (EmptyListException e) {
                currentMessage.setText("You don't have any items! The enemy powered up while you wasted your time!");
                enemy.powerUp(player.getLevel());
                healIndicator.setText("");
            } catch (DoesntExistException d) {
                currentMessage.setText("You don't have any potions! The enemy powered up while you wasted your time!");
                enemy.powerUp(player.getLevel());
                healIndicator.setText("");
            }
        } else {
            enemy.powerUp(1);
            currentMessage.setText("The enemy powered up while you wasted your time!");
            healIndicator.setText("");
        }
        battle();
    }

    // MODIFIES: this
    // EFFECTS: displays text options for item menu
    public void displayItemMenuText() {
        displayHud();
        potion = new JButton("Potion");
        potion.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 50, 200, 50);
        panel.add(potion);
        potion.addActionListener(this);
    }

    // ============================
    //        UI METHODS
    // ============================

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == attack) {
            processBattleCommand("1");
        } else if (e.getSource() == useItem) {
            processBattleCommand("2");
        } else if (e.getSource() == potion) {
            processItemCommand("1");
        } else if (e.getSource() == proceed) {
            this.dispose();
            MenuApp.middleMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: draws frame and sets frame parameters
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setTitle("Just play Dragon Quest, idiot.");
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: clears contents of the panel
    public void clearPanel() {
        panel.removeAll();
        validate();
        repaint();
        panel.setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: displays the hero and the enemy
    public void displayCharacters() {
        heroLabel = new JLabel();
        heroLabel.setIcon(heroSprite);
        heroLabel.setBounds(WIDTH - 256, HEIGHT / 2 - 128, 256, 256);
        enemyLabel = new JLabel();
        enemyLabel.setIcon(enemySprite);
        enemyLabel.setBounds(0, HEIGHT / 2 - 128, 256, 256);
        panel.add(heroLabel);
        panel.add(enemyLabel);
    }

    // MODIFIES: this
    // EFFECTS: sets enemy's sprite
    private void setEnemySprite() {
        tryEnemyOne();
        tryEnemyTwo();
    }

    // MODIFIES: this
    // EFFECTS: TWENTY FIVE LINES ISN'T ENOUGH TO DO THIS IN ONE SWITCH STATEMENT
    //          checks names of first set of enemies, helper for setEnemySprite
    private void tryEnemyOne() {
        switch (enemy.getName()) {
            case "Slime":
                enemySprite = new ImageIcon("./data/slime.png");
                break;
            case "Goblin":
                enemySprite = new ImageIcon("./data/goblin.png");
                break;
            case "Skeleton":
                enemySprite = new ImageIcon("./data/skeleton.png");
                break;
            case "Giant Worm":
                enemySprite = new ImageIcon("./data/worm.png");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: TWENTY FIVE LINES ISN'T ENOUGH TO DO THIS IN ONE SWITCH STATEMENT
    //          checks names of second set of enemies, helper for setEnemySprite
    private void tryEnemyTwo() {
        switch (enemy.getName()) {
            case "Giant Spider":
                enemySprite = new ImageIcon("./data/spider.png");
                break;
            case "Lich":
                enemySprite = new ImageIcon("./data/lich.png");
                break;
            case "Minotaur":
                enemySprite = new ImageIcon("./data/minotaur.png");
                break;
            case "Dragon":
                enemySprite = new ImageIcon("./data/dragon.png");
                break;
            case "Demon King":
                enemySprite = new ImageIcon("./data/king.png");
                break;
        }

    }

}
