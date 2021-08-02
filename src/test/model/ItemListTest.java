package model;

import exceptions.DoesntExistException;
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
        try {testList.addItem(new Item(Item.ItemType.POTION));}
        catch (FullItemListException e) {fail();}
    }

    @Test
    public void addItemYesException() {
        try {
            testList.addItem(new Item(Item.ItemType.POTION));
            testList.addItem(new Item(Item.ItemType.POTION));
            testList.addItem(new Item(Item.ItemType.POTION));
            testList.addItem(new Item(Item.ItemType.POTION));
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
        testList.addItem(new Item(Item.ItemType.POTION));
        testList.addItem(new Item(Item.ItemType.POTION));
        testList.addItem(new Item(Item.ItemType.POTION));
        String x = testList.listOutItems();
        assertEquals("(1) POTION (2) POTION (3) POTION ", x);
    }

    @Test
    public void removeItemIndexBigNumber() throws EmptyListException {
        try {
            this.testList.removeItem(testList.getMaxListSize() + 1);
            fail();
        } catch (TooBigNumberException e) { }
    }

    @Test
    public void removeItemIndexEmptyList() throws TooBigNumberException {
        try {
            this.testList.removeItem(0);
            fail();
        } catch (EmptyListException e) { }
    }

    @Test
    public void removeItemIndexSuccessful() throws  TooBigNumberException, EmptyListException, FullItemListException {
        testList.addItem(new Item(Item.ItemType.POTION));
        testList.addItem(new Item(Item.ItemType.POTION));
        testList.addItem(new Item(Item.ItemType.POTION));
        assertEquals(3, testList.itemListLength());
        testList.removeItem(0);
        assertEquals(2, testList.itemListLength());
    }

    @Test
    public void removeItemObjectSuccessful() throws EmptyListException, FullItemListException, DoesntExistException {
        testList.addItem(new Item(Item.ItemType.POTION));
        testList.addItem(new Item(Item.ItemType.POTION));
        assertEquals(2, testList.itemListLength());
        testList.removeItem(Item.ItemType.POTION);
        assertEquals(1, testList.itemListLength());
    }

    @Test
    public void removeItemObjectNotExistent() throws EmptyListException, FullItemListException {
        testList.addItem(new Item(Item.ItemType.POTION));
        testList.addItem(new Item(Item.ItemType.POTION));
        assertEquals(2, testList.itemListLength());
        try {
            testList.removeItem(Item.ItemType.ELIXIR);
            fail();
        } catch (DoesntExistException e) { }
    }

    @Test
    public void removeItemObjectEmptyList() throws FullItemListException, DoesntExistException {
        try {
            testList.removeItem(Item.ItemType.POTION);
            fail();
        } catch (EmptyListException e) { }
    }
}