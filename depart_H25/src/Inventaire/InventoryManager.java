package Item.Inventaire;

import Item.Exceptions.ExceptionInsufficientQuantityInStock;
import Item.Exceptions.ExceptionItemAlreadyExists;
import Item.Exceptions.ExceptionItemNotFound;
import Item.Item.Item;
import Item.Item.ItemBread;
import Item.Item.ItemEggs;
import Item.Item.ItemMilk;

public class InventoryManager {
    private final InventoryDatabase inventoryDatabase;

    public InventoryManager() {
        this.inventoryDatabase = new InventoryDatabase();
    }

    public void addNewBreadItem(int ID, String name, double price, String color, int weight) throws ExceptionItemAlreadyExists {
        ItemBread bread = new ItemBread(ID,name,price,color,weight);
        try {
            inventoryDatabase.findByItem(ID);
            throw new ExceptionItemAlreadyExists( ID);
        } catch (ExceptionItemNotFound e) {
            inventoryDatabase.insert(bread);
        }
    }
    public void addNewEggsItem(int ID, String name, double price, String color, int number) throws ExceptionItemAlreadyExists {
        ItemEggs eggs = new ItemEggs(ID,name,price,color,number);
        try {
            inventoryDatabase.findByItem(ID);
            throw new ExceptionItemAlreadyExists(ID);
        } catch (ExceptionItemNotFound e) {
            inventoryDatabase.insert(eggs);
        }
    }
    public void addNewMilkItem(int ID, String name, double price, double fat, double liters){
        ItemMilk milk = new ItemMilk(ID,name,price,fat,liters);
        try {
            inventoryDatabase.findByItem(ID);
            throw new ExceptionItemAlreadyExists(ID);
        } catch (ExceptionItemNotFound e) {
            inventoryDatabase.insert(milk);
        }
    }
    public void removeItem(int ID){
        inventoryDatabase.remove(ID);
    }
    public void increaseItemQuantity(int ID, int quantity) {
        Item item = inventoryDatabase.findByItem(ID);
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot decrease by a negative amount.");
        }
        item.increaseQuantityInStock(quantity);
    }
    public void decreaseItemQuantity(int ID, int quantity) throws ExceptionInsufficientQuantityInStock {
        Item item = inventoryDatabase.findByItem(ID);
        if (item.getQuantityInStock() < quantity){
            throw new ExceptionInsufficientQuantityInStock(quantity);
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot decrease by a negative amount.");
        }
        item.decreaseQuantityInStock(quantity);
    }
    public Item getItem(int ID) {
        return inventoryDatabase.findByItem(ID);
    }
    public Item[] getArrayOfItems() {
        return inventoryDatabase.getArrayOfItems();
    }
}
