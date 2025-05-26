package Item.Inventaire;

import Item.Exceptions.ExceptionItemAlreadyExists;
import Item.Exceptions.ExceptionItemNotFound;
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
            throw new ExceptionItemAlreadyExists("A bread item, in this database, already has ID: " + ID);
        } catch (ExceptionItemNotFound e) {
            inventoryDatabase.insert(bread);
        }
    }
    public void addNewEggsItem(int ID, String name, double price, String color, int number) throws ExceptionItemAlreadyExists {
        ItemEggs eggs = new ItemEggs(ID,name,price,color,number);
        try {
            inventoryDatabase.findByItem(ID);
            throw new ExceptionItemAlreadyExists("A eggs item, in this database, already has ID: " + ID);
        } catch (ExceptionItemNotFound e) {
            inventoryDatabase.insert(eggs);
        }
    }
    public void addNewMilkItem(int ID, String name, double price, double fat, double liters){
        ItemMilk milk = new ItemMilk(ID,name,price,fat,liters);
        try {
            inventoryDatabase.findByItem(ID);
            throw new ExceptionItemAlreadyExists("A milk item, in this database, already has ID: " + ID);
        } catch (ExceptionItemNotFound e) {
            inventoryDatabase.insert(milk);
        }
    }
}
