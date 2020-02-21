
public class OrdinaryRoom extends Room {
    private int breakfastCount = 0;
    private int breakfast = 180;
    private static double basicPrice = 500;


    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBreakfastCount(int breakfastCount) {
        this.breakfastCount = breakfastCount;
    }

    public static void setBasicPrice(double bP) {
        basicPrice = bP;
    }

    @Override
    public void checkIn(int number) {
        setCheckIn(true);
        setBreakfastCount(number);
    }

    @Override
    public void checkOut() {
        setCheckIn(false);
    }

    @Override
    public double pricePerNight(Day day) {
        return basicPrice * day.getRate() + breakfast * breakfastCount;
    }

    @Override
    public String toString() {
        return "O "+getNumber()+" "+breakfastCount;
    }
}
