package Item.Item;

public class ItemEggs extends Item{
    protected String color;
    protected int number;

    public ItemEggs (int ID, String name, double price, String color, int number){
        super(ID,name,price);
        this.color = color;
        this.number = number;
    }

    @Override
    public Category getCategory() {
        return Category.Eggs;
    }

    @Override
    public String getCategoryString() {
        return Category.Eggs.name();
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public String infoToString() {
        return super.infoToString() +
                "Color: \t" + color + "\n" +
                "Number: \t"+ number + " \n ";
    }
}
