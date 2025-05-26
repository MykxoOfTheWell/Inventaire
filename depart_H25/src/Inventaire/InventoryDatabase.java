package Item.Inventaire;
import Item.Exceptions.ExceptionItemNotFound;
import Item.Item.Item;

public class InventoryDatabase {
    private int itemsCount;
    private InventoryDatabaseNode first;

    public InventoryDatabase(){
        this.itemsCount = 0;
        this.first = null;
    }

    public void insert(Item item) {
        InventoryDatabaseNode newNode = new InventoryDatabaseNode(item);
        if (first == null) {
            first = newNode;
        }
        newNode.next=first;
        first = newNode;
        this.itemsCount++;
    }
    public Item findByItem (int ID) throws ExceptionItemNotFound {
        InventoryDatabaseNode currentItem = first;
        while (currentItem.item.getID() != ID) {
            if (currentItem.next == null) {
                throw new ExceptionItemNotFound("No item with ID: '" + ID +"' was found.");
            }
            currentItem = currentItem.next;
        }
        return currentItem.item;
    }
    public void remove(int ID) throws ExceptionItemNotFound {
        if (ID < 0) {
            throw new IllegalArgumentException("ID cannot be a negative number");
        }
        InventoryDatabaseNode currentItem = first;
        InventoryDatabaseNode previousItem = null;
        while (currentItem != null && currentItem.item.getID() != ID) {
            previousItem = currentItem;
            currentItem = currentItem.next;
        }

        if (currentItem == null) {
            throw new ExceptionItemNotFound("No item with ID: '" + ID + "' could be removed.");
        }

        if (previousItem == null) {
            first = currentItem.next;
        } else {
            previousItem.next = currentItem.next;
        }
        this.itemsCount--;
    }

    public Item[] getArrayOfItems() {
        Item[] array = new Item[itemsCount];
        InventoryDatabaseNode currentItem = first;
        int i = 0;

        // Ajout d'une verification du current item si itemCount est out of sync.
        while (currentItem != null && i < itemsCount) {
            array[i++] = currentItem.item;
            currentItem = currentItem.next;
        }

        return array;
    }


}
