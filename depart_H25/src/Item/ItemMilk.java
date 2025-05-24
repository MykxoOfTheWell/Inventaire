package Item.Item;

public class ItemMilk extends Item{
    protected double fat;
    protected double liters;

    public ItemMilk (int ID, String name, double price, double fat, double liters){
        super(ID,name,price);
        this.fat = fat;
        this.liters = liters;
    }

    @Override
    public Category getCategory() {
        return Category.Milk;
    }

    @Override
    public String getCategoryString() {
        return Category.Milk.name();
    }

    public double getLiters() {
        return liters;
    }
    public void setLiters(double liters) {
        this.liters = liters;
    }

    public double getFat() {
        return fat;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }

    public String infoToString(){
        return super.infoToString() +
                "Fat: \t" + fat + "\n" +
                "Liters: \t"+ liters + " \n ";
    }
}
