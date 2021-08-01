package model;

import exceptions.EmptyListException;
import exceptions.FullItemListException;
import exceptions.TooBigNumberException;

import java.util.ArrayList;

// Represents a list of items
public class ItemList {
    private ArrayList<Item> itemList;
    private int maxListSize = 3;

    // EFFECTS: constructs a new empty list of items
    public ItemList() {
        this.itemList = new ArrayList<>();
    }

    public int itemListLength() {
        int x = 0;
        for (Item i : this.itemList) {
            x += 1;
        }
        return x;
    }

    // MODIFIES: this
    // EFFECTS: adds Item item to the list
    public void addItem(Item item) throws FullItemListException {
        if (itemList.size() == maxListSize) {
            throw new FullItemListException();
        } else {
            itemList.add(item);
        }
    }

    // EFFECTS: lists out the types of all the items inside the itemlist
    public String listOutItems() {
        String list = "";
        int index = 1;
        for (Item i : this.itemList) {
            list += "(" + String.valueOf(index) + ") " + i.getType() + " ";
            index += 1;
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: removes item at index x from itemlist
    public void removeItem(int x) throws TooBigNumberException, EmptyListException {
        if (x > this.itemListLength()) {
            throw new TooBigNumberException();
        } else if (this.itemListLength() == 0) {
            throw new EmptyListException();
        } else {
            this.itemList.remove(x);
        }
    }

    public int getMaxListSize() {
        return this.maxListSize;
    }

}
