package ui;

import model.Item;
import model.ItemList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Class responsible for drawing elements of the title screen
public class TitleFrame extends JFrame implements ActionListener {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 700;
    private static JPanel panel;
    private static JLabel title;
    private static JTextField name;
    private static JButton start;
    private static JButton load;
    private static JButton quit;
    private static JButton okay;
    private static JButton continueJourney;
    private static JButton stats;
    private static JButton rest;
    private static JButton manage;
    private static JButton save;
    private static JButton submit;
    private static JButton enterStory;
    private static JButton continueOn;
    private static JButton addItems;
    private static JButton removeItems;
    private static JLabel current;
    private static JLabel level;
    private static JLabel health;
    private static JLabel exp;
    private static JLabel toLevel;
    private static JLabel attack;
    private static JLabel defense;
    private static JLabel items;
    private static final ImageIcon icon = new ImageIcon("./data/title_icon.png");
    private static final ImageIcon titleImage = new ImageIcon("./data/title_image.png");

    // constructor that calls the initializer
    public TitleFrame() {
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: initializes the JFrame window, adds panel
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
    // EFFECTS: draws the title menu with start, load, quit game
    public void drawTitleMenu() {
        panel.setLayout(null);
        drawTitleGraphic();
        start = new JButton("Start Game");
        start.setBounds(WIDTH / 2 - 100, HEIGHT / 2, 200, 50);
        start.addActionListener(this);
        load = new JButton("Load Game");
        load.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 50, 200, 50);
        load.addActionListener(this);
        quit = new JButton("Quit Game");
        quit.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 100, 200, 50);
        quit.addActionListener(this);
        panel.add(start);
        panel.add(load);
        panel.add(quit);
    }

    // MODIFIES: this
    // EFFECTS: draws game start, including text for new character
    public void drawStartGameMenu() {
        clearPanel();
        drawTitleGraphic();
        JLabel enter = new JLabel("Please enter your name.");
        enter.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 50, 500, 20);
        name = new JTextField();
        name.setPreferredSize(new Dimension(250, 40));
        name.setBounds(WIDTH / 2 - 125, HEIGHT / 2, 250, 40);
        submit = new JButton("Submit");
        submit.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 50, 200, 50);
        submit.addActionListener(this);
        panel.add(enter);
        panel.add(name);
        panel.add(submit);
    }

    // MODIFIES: this
    // EFFECTS: draws a menu to show that game has been loaded
    public void drawSuccessfulLoadGameMenu() {
        clearPanel();
        drawTitleGraphic();
        JLabel loaded = new JLabel("Successfully loaded game");
        loaded.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 50, 500, 20);
        okay = new JButton("Continue");
        okay.setBounds(WIDTH / 2 - 100, HEIGHT / 2, 200, 50);
        okay.addActionListener(this);
        panel.add(loaded);
        panel.add(okay);
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
    // EFFECTS: draws the title graphic
    public void drawTitleGraphic() {
        title = new JLabel();
        title.setIcon(titleImage);
        title.setSize(1024, 256);
        panel.add(title);
    }

    // MODIFIES: this, player
    // EFFECTS: sets player name, displays welcome message, begins story
    public void newGame() {
        MenuApp.getPlayer().setName(name.getText());
        clearPanel();
        drawTitleGraphic();
        JLabel welcome = new JLabel("Welcome, " + MenuApp.getPlayer().getName());
        welcome.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 50, 500, 20);
        panel.add(welcome);
        enterStory = new JButton("Continue");
        enterStory.setBounds(WIDTH / 2 - 100, HEIGHT / 2 + 50, 200, 50);
        enterStory.addActionListener(this);
        panel.add(enterStory);
    }

    // MODIFIES: this, player
    // EFFECTS: draws stuff for middle menu
    public void displayMiddleMenu() {
        clearPanel();
        continueJourney = new JButton("Continue Journey");
        continueJourney.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 300, 400, 50);
        stats = new JButton("View Character Stats");
        stats.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 250, 400, 50);
        rest = new JButton("Rest to regain HP");
        rest.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 200, 400, 50);
        manage = new JButton("Manage Items");
        manage.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 150, 400, 50);
        save = new JButton("Save Game");
        save.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 100, 400, 50);
        quit = new JButton("Quit Game");
        quit.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 50, 400, 50);
        panel.add(continueJourney);
        panel.add(stats);
        panel.add(rest);
        panel.add(manage);
        panel.add(save);
        panel.add(quit);
        middleMenuButtonProcess();
    }

    // MODIFIES: this, player
    // EFFECTS: processes button presses for middle menu
    public void middleMenuButtonProcess() {
        continueJourney.addActionListener(this);
        stats.addActionListener(e -> MenuApp.processMiddleMenuCommand("2"));
        rest.addActionListener(e -> MenuApp.processMiddleMenuCommand("3"));
        manage.addActionListener(e -> MenuApp.processMiddleMenuCommand("4"));
        save.addActionListener(e -> MenuApp.processMiddleMenuCommand("5"));
        quit.addActionListener(e -> MenuApp.processMiddleMenuCommand("6"));
    }

    // MODIFIES: this
    // EFFECTS: shows stats of the player
    public void displayStats() {
        clearPanel();
        setStatTexts();
        setStatCustoms();
        addStatsToPanel();
        continueOn = new JButton("Continue");
        continueOn.setBounds(WIDTH / 2 - 100, 450, 200, 50);
        panel.add(continueOn);
        continueOn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: plugs stats into field labels
    public void setStatTexts() {
        current = new JLabel("Your  current stats:");
        level = new JLabel("Level: " + MenuApp.getPlayer().getLevel());
        health = new JLabel("Current HP: " + MenuApp.getPlayer().getHealth() + "/"
                + MenuApp.getPlayer().getMaxHealth());
        exp = new JLabel("Experience: " + MenuApp.getPlayer().getExperience());
        toLevel = new JLabel("XP needed for next level: " + MenuApp.getPlayer().getExpToLevelUp());
        attack = new JLabel("Attack: " + MenuApp.getPlayer().getAttack());
        defense = new JLabel("Defense: " + MenuApp.getPlayer().getDefense());
        items = new JLabel("Equipped items: " + MenuApp.getPlayer().getItems().listOutItems());
    }

    // MODIFIES: this
    // EFFECTS: sets stat bounds and fonts
    public void setStatCustoms() {
        current.setBounds(WIDTH / 2 - 200, 50, 1024, 50);
        current.setFont(new Font("Helvetica", Font.PLAIN, 20));
        level.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        level.setFont(new Font("Helvetica", Font.PLAIN, 20));
        health.setBounds(WIDTH / 2 - 200, 150, 1024, 50);
        health.setFont(new Font("Helvetica", Font.PLAIN, 20));
        exp.setBounds(WIDTH / 2 - 200, 200, 1024, 50);
        exp.setFont(new Font("Helvetica", Font.PLAIN, 20));
        toLevel.setBounds(WIDTH / 2 - 200, 250, 1024, 50);
        toLevel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        attack.setBounds(WIDTH / 2 - 200, 300, 1024, 50);
        attack.setFont(new Font("Helvetica", Font.PLAIN, 20));
        defense.setBounds(WIDTH / 2 - 200, 350, 1024, 50);
        defense.setFont(new Font("Helvetica", Font.PLAIN, 20));
        items.setBounds(WIDTH / 2 - 200, 400, 1024, 50);
        items.setFont(new Font("Helvetica", Font.PLAIN, 20));
    }

    // MODIFIES: this
    // EFFECTS: adds labels to panel
    public void addStatsToPanel() {
        panel.add(current);
        panel.add(level);
        panel.add(health);
        panel.add(exp);
        panel.add(toLevel);
        panel.add(attack);
        panel.add(defense);
        panel.add(items);
    }

    // MODIFIES: this
    // EFFECTS: shows menu for resting character
    public void displayRestMenu() {
        clearPanel();
        JLabel rested = new JLabel("Character healed to full");
        rested.setBounds(WIDTH / 2 - 200, 300, 1024, 50);
        rested.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(rested);
        continueOn = new JButton("Continue");
        continueOn.setBounds(WIDTH / 2 - 100, 450, 200, 50);
        panel.add(continueOn);
        continueOn.addActionListener(this);
    }

    // MODIFIES: this, player
    // EFFECTS: shows menu for adding/removing items
    public void displayItemsPrompt() {
        clearPanel();
        items = new JLabel("Equipped items: " + MenuApp.getPlayer().getItems().listOutItems());
        items.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        items.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(items);
        addItems = new JButton("Add Items");
        addItems.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 150, 400, 50);
        removeItems = new JButton("Remove Items");
        removeItems.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 100, 400, 50);
        panel.add(addItems);
        panel.add(removeItems);
        addItems.addActionListener(e -> MenuApp.displayItemOptions());
        removeItems.addActionListener(e -> MenuApp.displayRemoveItems());
    }

    // MODIFIES: this, player
    // EFFECTS: shows menu for equipping items
    public void displayEquipPrompt() {
        clearPanel();
        JLabel which = new JLabel("What would you like to equip?");
        which.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        which.setFont(new Font("Helvetica", Font.PLAIN, 20));
        JButton potion = new JButton("Potion");
        potion.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 150, 400, 50);
        potion.addActionListener(e -> MenuApp.equipItems("1"));
        JButton elixir = new JButton("Elixir");
        elixir.setBounds(WIDTH / 2 - 200, HEIGHT / 2 - 100, 400, 50);
        elixir.addActionListener(e -> MenuApp.equipItems("2"));
        panel.add(which);
        panel.add(potion);
        panel.add(elixir);
    }

    // MODIFIES: this
    // EFFECTS: shows success when adding item
    public void displaySuccessAddItem() {
        clearPanel();
        JLabel good = new JLabel("Successfully added item");
        good.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        good.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(good);
        continueOn = new JButton("Continue");
        continueOn.setBounds(WIDTH / 2 - 100, 450, 200, 50);
        panel.add(continueOn);
        continueOn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: displays exception for full items list while adding items
    public void displayFullItemScreen() {
        clearPanel();
        JLabel uhOh = new JLabel("Your items list is full!");
        uhOh.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        uhOh.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(uhOh);
        continueOn = new JButton("Continue");
        continueOn.setBounds(WIDTH / 2 - 100, 450, 200, 50);
        panel.add(continueOn);
        continueOn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: shows menu for removing items
    public void displayRemovePrompt() {
        clearPanel();
        JLabel which = new JLabel("Which item would you like to remove?");
        which.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        which.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(which);
        JLabel itemList = new JLabel(MenuApp.getPlayer().getItems().listOutItems());
        itemList.setBounds(WIDTH / 2 - 200, 150, 1024, 50);
        itemList.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(itemList);
        JTextField number = new JTextField();
        number.setBounds(WIDTH / 2 - 200, 250, 400, 50);
        panel.add(number);
        JButton enter = new JButton("Remove");
        enter.setBounds(WIDTH / 2 - 100, 350, 200, 50);
        panel.add(enter);
        enter.addActionListener(e -> MenuApp.removeItems(number.getText()));
    }

    // MODIFIES: this
    // EFFECTS: shows success for removing item
    public void displaySuccessRemoveItem() {
        clearPanel();
        JLabel good = new JLabel("Successfully removed item");
        good.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        good.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(good);
        continueOn = new JButton("Continue");
        continueOn.setBounds(WIDTH / 2 - 100, 450, 200, 50);
        panel.add(continueOn);
        continueOn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: shows exception for empty items list while removing items
    public void displayNoItemsScreen() {
        clearPanel();
        JLabel uhOh = new JLabel("Your items list is empty!");
        uhOh.setBounds(WIDTH / 2 - 200, 100, 1024, 50);
        uhOh.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(uhOh);
        continueOn = new JButton("Continue");
        continueOn.setBounds(WIDTH / 2 - 100, 450, 200, 50);
        panel.add(continueOn);
        continueOn.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: checks for the press of a button or a text entry
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            MenuApp.startGame();
        } else if (e.getSource() == load) {
            MenuApp.loadGame();
        } else if (e.getSource() == quit) {
            System.exit(0);
        } else if (e.getSource() == okay) {
            this.dispose();
            new Story(MenuApp.getPlayer());
        } else if (e.getSource() == submit) {
            newGame();
        } else if (e.getSource() == enterStory) {
            this.dispose();
            new Story(MenuApp.getPlayer());
        } else if (e.getSource() == continueOn) {
            this.dispose();
            MenuApp.middleMenu();
        } else if (e.getSource() == continueJourney) {
            this.dispose();
            new Story(MenuApp.getPlayer());
        }
    }
}
