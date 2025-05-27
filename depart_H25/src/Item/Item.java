package Item.Item;
public abstract class Item {
    private int ID;
    private String name;
    private double price;
    private int quantityInStock;

    public Item(int ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public abstract Category getCategory();
    public abstract String getCategoryString();

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public void increaseQuantityInStock(int quantity) {
        this.quantityInStock += quantity;
    }
    public void decreaseQuantityInStock(int quantityInStock){
        increaseQuantityInStock(-quantityInStock);
    }

    public String infoToString() {
        return  "ID: \t" + this.getID() + "\n" +
                "Name: \t" + this.getName() + "\n" +
                "Price: \t" + this.getPrice() + "\n" +
                "In stock: \t" + this.getQuantityInStock() + "\n";
    }
    public String toString() {
        return ID+": " + this.getCategory() + " - " + this.getName() + " ("+this.quantityInStock+")" ;
    }
}