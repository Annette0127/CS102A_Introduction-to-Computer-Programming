public class Food {
    public static void main(String[] args) {
        Food food= new Food();
        food.setName("pizza");
        food.setType("Seafood");
        food.setSize(11);
        food.setPrice(120);
        food.showInformation();
        food.setName("pizza");
        food.setType("Beef");
        food.setSize(9);
        food.setPrice(100);
        food.showInformation();
        food.setName("fried Rice");
        food.setType("Seafood");
        food.setSize(5);
        food.setPrice(40);
        food.showInformation();
        food.setName("noodle");
        food.setType("Beef");
        food.setSize(6);
        food.setPrice(35);
        food.showInformation();
    }

    private String name;
    private String type;
    private int size;
    private double price;

    public void showInformation() {
        System.out.printf("%s %s:(%d Inches) %.2f %c\n", type, name, size, price,'\u0024');
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String na) {
        name = na;
    }

    public void setType(String ty) {
        type = ty;
    }

    public void setSize(int si) {
        size = si;
    }

    public void setPrice(double pr) {
        price = pr;
    }
}
