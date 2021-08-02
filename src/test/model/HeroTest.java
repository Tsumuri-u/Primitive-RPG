package model;

import exceptions.DoesntExistException;
import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {
    private Hero hero;

    @BeforeEach
    void runBefore() throws FullItemListException {
        hero = new Hero("Joe");
    }

    @Test
    void testLevelUp() {
        hero.levelUp();
        assertEquals(2, hero.getLevel());
        assertEquals(110, hero.getMaxHealth());
        assertEquals(12, hero.getAttack());
        assertEquals(3, hero.getDefense());
        assertEquals(250, hero.getExpToLevelUp());
    }

    @Test
    void testUsePotionSuccessfulNotMax() throws FullItemListException, EmptyListException, DoesntExistException {
        hero.setHealth(1);
        hero.addItem(new Item(Item.ItemType.POTION));
        assertEquals(1, hero.getItems().itemListLength());
        assertEquals(1, hero.getHealth());

        hero.usePotion();
        assertEquals(21, hero.getHealth());
    }

    @Test
    void testUsePotionSuccessfulMax() throws FullItemListException, EmptyListException, DoesntExistException {
        hero.setHealth(90);
        hero.addItem(new Item(Item.ItemType.POTION));
        assertEquals(1, hero.getItems().itemListLength());
        assertEquals(90, hero.getHealth());

        hero.usePotion();
        assertEquals(100, hero.getHealth());
    }

    @Test
    void testUsePotionDoesntExist() throws FullItemListException, EmptyListException {
        hero.setHealth(90);
        hero.addItem(new Item(Item.ItemType.ELIXIR));
        assertEquals(90, hero.getHealth());

        try {
            hero.usePotion();
            fail();
        } catch (DoesntExistException d) { }

    }

    @Test
    void testUsePotionEmptyList() throws EmptyListException, DoesntExistException {
        hero.setHealth(90);
        assertEquals(90, hero.getHealth());

        try {
            hero.usePotion();
            fail();
        } catch (EmptyListException e) { }
    }

    @Test
    void testSetExperience() {
        hero.setExperience(10);
        assertEquals(10, hero.getExperience());
    }

    @Test
    void testSetExpToLevelUp() {
        hero.setExpToLevelUp(1000);
        assertEquals(1000, hero.getExpToLevelUp());
    }

    @Test
    void testSetName() {
        assertEquals("Joe", hero.getName());
        hero.setName("Bob");
        assertEquals("Bob", hero.getName());
    }

    @Test
    void testRemoveItem() throws FullItemListException, TooBigNumberException, EmptyListException{
        hero.addItem(new Item(Item.ItemType.POTION));
        assertEquals(1, hero.getItems().itemListLength());
        hero.removeItem(0);
        assertEquals(0, hero.getItems().itemListLength());
    }

    // TODO: fix this shit
    @Test
    void testAttack() {
        assertEquals(10, hero.attack());
    }

    // TODO: fix this shit
    @Test
    void testDefense() {
        assertEquals(2, hero.defend());
    }
}
