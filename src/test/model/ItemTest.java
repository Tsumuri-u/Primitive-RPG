package model;

import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;

    @BeforeEach
    void runBefore() {
        item = new Item(Item.ItemType.POTION);
    }

    @Test
    void testGetType() {
        assertEquals(Item.ItemType.POTION, item.getType());
    }
}
