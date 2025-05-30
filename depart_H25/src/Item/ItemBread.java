package Item.Item;

public class ItemBread extends Item{
    protected String color;
    protected double weight;

    public ItemBread (int ID, String name, double price, String color, double weight){
        super(ID, name, price);
        this.color = color;
        this.weight = weight;
    }

    @Override
    public Category getCategory() {
        return Category.Bread;
    }

    @Override
    public String getCategoryString() {
        return Category.Bread.name();
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String infoToString() {
        return super.infoToString() +
                "Color: \t" + color + "\n" +
                "Weight: \t"+ weight + "\n";

    }
}
