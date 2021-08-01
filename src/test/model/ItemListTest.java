package model;

import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {
    private ItemList testList;

    @BeforeEach
    void runBefore() {
        testList = new ItemList();
    }

    @Test
    public void addItemNoException() {
        try {testList.addItem(new Item("Potion", 20));}
        catch (FullItemListException e) {fail();}
    }

    @Test
    public void addItemYesException() {
        try {
            testList.addItem(new Item("Potion", 20));
            testList.addItem(new Item("Potion", 20));
            testList.addItem(new Item("Potion", 20));
            testList.addItem(new Item("Potion", 20));
            fail();
        }
        catch (FullItemListException e) { }
    }

    @Test
    public void listOutItemsEmpty() {
        String x = testList.listOutItems();
        assertEquals("", x);
    }

    @Test
    public void listOutItemsNotEmpty() throws FullItemListException {
        testList.addItem(new Item("Potion", 20));
        testList.addItem(new Item("Potion", 20));
        testList.addItem(new Item("Potion", 20));
        String x = testList.listOutItems();
        assertEquals("(1) Potion (2) Potion (3) Potion ", x);
    }

    @Test
    public void removeItemBigNumber() throws EmptyListException {
        try {
            this.testList.removeItem(testList.getMaxListSize() + 1);
            fail();
        } catch (TooBigNumberException e) { }
    }

    @Test
    public void removeItemEmptyList() throws TooBigNumberException {
        try {
            this.testList.removeItem(0);
            fail();
        } catch (EmptyListException e) { }
    }

    @Test
    public void removeItemSuccessful() throws  TooBigNumberException, EmptyListException, FullItemListException {
        testList.addItem(new Item("Potion", 20));
        testList.addItem(new Item("Potion", 20));
        testList.addItem(new Item("Potion", 20));
        assertEquals(3, testList.itemListLength());
        testList.removeItem(0);
        assertEquals(2, testList.itemListLength());
    }
}