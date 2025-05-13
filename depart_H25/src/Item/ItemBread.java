package Item.Item;

public class ItemBread extends Item{
    protected String color;
    protected double weight;

    public ItemBread (int ID, String name, double price, String color, int weight){
        super(ID,name,name,price);
        this.color = color;
        this.weight = weight;
    }
}
