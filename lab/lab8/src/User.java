public class User {
    private String name;
    private String password;
    private double money;

    public static void main(String arg[]) {
            User user = new User();
            user.setName("Lucy");
            user.setPassword("123456");
            user.setMoney(1000);
            user.introduce();
            user.expense(2000);
            user.expense(500);
            user.income(1000);
            user.introduce();

    }

    public void introduce() {
        System.out.printf("My name is %s and I have %.2f dollar\n",name,money);
    }

    public void expense(double decrease) {
        if(money-decrease>=0){
            money = money-decrease;
            System.out.printf("You have expense %.2f dollar and the remained amount is %.2f\n",decrease,money);
        }else {System.out.println("no sufficient funds");}
    }

    public void income(double increase) {
        money = money+increase;
        System.out.printf("The remained amount is %.2f\n",money);
    }

    public double getMoney(){
        return money;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public void setMoney(double moneyy){
        money = moneyy;
    }
    public void setName(String namee){
        name = namee;
    }
    public void setPassword(String pass){
        password = pass;
    }
}
