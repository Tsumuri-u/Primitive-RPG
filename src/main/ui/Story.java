package ui;

import model.Enemy;
import model.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Class that contains the story logic and UI for the game
public class Story extends JFrame implements ActionListener {
    private Hero player;
    private List<String> text;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 700;
    private static JPanel panel;
    private static JButton progress;
    private static JLabel storyText;
    private static final ImageIcon icon = new ImageIcon("./data/title_icon.png");

    // Instantiates the story with the player character
    public Story(Hero player) {
        this.player = player;
        initializeGraphics();
        startStory();
    }

    // EFFECTS: continues where the story left off
    private void startStory() {
        checkPartOne(player);
        checkPartTwo(player);
        checkPartThree(player);
    }

    // EFFECTS: checks if player has progressed through Part 1
    private void checkPartOne(Hero player) {
        int progress = player.getStoryProgress();
        switch (progress) {
            case 0:
                intro();
                break;
            case 1:
                outsideOne();
                break;
            case 2:
                outsideTwo();
                break;
            case 3:
                outsideThree();
                break;
            case 4:
                outsideFour();
                break;
        }
    }

    // EFFECTS: checks if player has progressed through Part 2
    private void checkPartTwo(Hero player) {
        int progress = player.getStoryProgress();
        switch (progress) {
            case 5:
                towerOne();
                break;
            case 6:
                towerTwo();
                break;
            case 7:
                towerThree();
                break;
        }
    }

    // EFFECTS: checks if player has progressed through Part 3
    private void checkPartThree(Hero player) {
        int progress = player.getStoryProgress();
        switch (progress) {
            case 8:
                lairOne();
                break;
            case 9:
                lairTwo();
                break;
            case 10:
                finale();
                break;
        }
    }

    // EFFECTS: prints the intro to the story
    private void intro() {
        player.setStoryProgress(1);
        text = new ArrayList<>();
        String string1 = (player.getName() + ", the world is in trouble, it is up to you to save it!");
        String string2 = ("Make your way to the Demon King and defeat him!");
        text.add(string1);
        text.add(string2);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e -> outsideOne());
//        MenuApp.pressToContinue();

    }

    // EFFECTS: prints the first part of the "Outside" arc, where a slime attacks
    private void outsideOne() {
        text = new ArrayList<>();
        String string1 = ("You embark on your journey toward the Demon King's castle.");
        String string2 = ("You walk the grassy fields outside the kingdom.");
        String string3 = ("Suddenly, a slime appears!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                displayBattle(new Enemy(30, "Slime", 150, 5, 1)));
//        MenuApp.pressToContinue();
//        MenuApp.middleMenu();
//        outsideTwo();

    }

    // EFFECTS: prints the second part of the "Outside" arc, where a goblin attacks
    private void outsideTwo() {
        text = new ArrayList<>();
        String string1 = ("After defeating the slime, you continue your journey.");
        String string2 = ("Your journey leads you to a forest.");
        String string3 = ("Suddenly, a goblin jumps out of a bush and attacks!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                displayBattle(new Enemy(50, "Goblin", 250, 7, 3)));
//        MenuApp.pressToContinue();
//        MenuApp.middleMenu();
//        outsideThree();
    }

    // EFFECTS: prints the third part of the "Outside" arc, where a skeleton attacks
    private void outsideThree() {
        text = new ArrayList<>();
        String string1 = ("After defeating the goblin, you continue your journey.");
        String string2 = ("Night falls and the air gets very misty.");
        String string3 = ("Out of the mist, a skeleton appears and attacks!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                displayBattle(new Enemy(60, "Skeleton", 400, 10, 5)));
//        MenuApp.pressToContinue();
//        MenuApp.middleMenu();
//        outsideFour();
    }

    // EFFECTS: prints the fourth part of the "Outside" arc, where a giant worm attacks
    private void outsideFour() {
        text = new ArrayList<>();
        String string1 = "After defeating the skeleton, you continue your journey.";
        String string2 = "Day breaks and you begin making your way through a sandy desert.";
        String string3 = "Suddenly, a giant worm appears out of the ground and attacks!";
        text.add(string1);
        text.add(string2);
        text.add(string3);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                 displayBattle(new Enemy(70, "Giant Worm", 800, 13, 8)));
//        MenuApp.pressToContinue();
//        MenuApp.middleMenu();
//        towerOne();
    }

    // EFFECTS: prints the first part of the "Tower" arc, where a giant spider attacks
    private void towerOne() {
        text = new ArrayList<>();
        String string1 = ("After defeating the giant worm, you continue your journey.");
        String string2 = ("You come across a tower in the middle of the desert.");
        String string3 = ("You enter the tower and make your way to the top.");
        String string4 = ("Suddenly, a giant spider quickly crawls its way toward you!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        text.add(string4);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                   displayBattle(new Enemy(80, "Giant Spider", 1000, 15, 9)));
//        MenuApp.pressToContinue();
//        MenuApp.middleMenu();
//        towerTwo();
    }

    // EFFECTS: prints the second part of the "Tower" arc, where a lich attacks
    private void towerTwo() {
        text = new ArrayList<>();
        String string1 = ("After defeating the giant spider, you continue up the tower");
        String string2 = ("You enter a dark lab full of books and materials");
        String string3 = ("Suddenly, an angered lich appears and attacks!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                 displayBattle(new Enemy(90, "Lich", 1300, 17, 10)));
//        MenuApp.pressToContinue();
//        MenuApp.middleMenu();
//        towerThree();
    }

    // EFFECTS: prints the third part of the "Tower" arc, where a minotaur attacks
    private void towerThree() {
        text = new ArrayList<>();
        String string1 = ("After defeating the lich, you continue up the tower");
        String string2 = ("You find yourself in a large dilapidated dungeon.");
        String string3 = ("From the corner, a minotaur attacks!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                 displayBattle(new Enemy(100, "Minotaur", 1600, 19, 12)));
//        MenuApp.pressToContinue();
//        MenuApp.middleMenu();
//        lairOne();
    }

    // EFFECTS: prints the first part of the "Lair" arc, where a dragon attacks
    private void lairOne() {
        text = new ArrayList<>();
        String string1 = ("After defeating the minotaur, you continue up the tower.");
        String string2 = ("You reach the top of the tower, and are met with the castle of the Demon King.");
        String string3 = ("You enter his lair, making your way toward the king's chamber.");
        String string4 = ("A dragon guards the path ahead.");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        text.add(string4);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                    displayBattle(new Enemy(120, "Dragon", 2000, 20, 14)));
//        MenuApp.middleMenu();
//        MenuApp.pressToContinue();
//        lairTwo();
    }

    // EFFECTS: prints the second part of the "Lair" arc, where the Demon King attacks
    private void lairTwo() {
        text = new ArrayList<>();
        String string1 = ("After defeating the dragon, you make your way through the castle.");
        String string2 = ("You enter the throne room.");
        String string3 = ("Finally, you come face to face with the Demon King.");
        String string4 = ("\"This is where your journey ends, hero.\"");
        String string5 = ("Defeat the Demon King!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        text.add(string4);
        text.add(string5);
        displayStory(text);
        progress = new JButton("Proceed");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        panel.add(progress);
        progress.addActionListener(e ->
                 displayBattle(new Enemy(150, "Demon King", 3000, 22, 15)));
//        MenuApp.pressToContinue();
//        finale();
    }

    // EFFECTS: prints the finale
    private void finale() {
        text = new ArrayList<>();
        String string1 = ("The Demon King has been defeated!");
        String string2 = ("The kingdom has been saved!");
        String string3 = ("Congratulations, hero!");
        text.add(string1);
        text.add(string2);
        text.add(string3);
        displayStory(text);
        progress = new JButton("Finish Game");
        progress.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        progress.addActionListener(e -> System.exit(0));
        panel.add(progress);
//        MenuApp.pressToContinue();
//        System.exit(0);
    }

    // ============================
    //        UI METHODS
    // ============================

    @Override
    public void actionPerformed(ActionEvent e) {

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
    // EFFECTS: draws story text
    public void displayStory(List<String> text) {
        clearPanel();
        int offset = 0;
        for (String s : text) {
            storyText = new JLabel();
            storyText.setText(s);
            storyText.setFont(new Font("Helvetica", Font.PLAIN, 20));
            storyText.setBounds(200, HEIGHT / 2 - 300 + offset, 1024, 100);
            panel.add(storyText);
            offset += 50;
        }
    }

    // MODIFIES: this
    // EFFECTS: opens battle menu and disposes of this GUI window
    public void displayBattle(Enemy e) {
        this.dispose();
        new BattleMenu(player, e);
    }
}
