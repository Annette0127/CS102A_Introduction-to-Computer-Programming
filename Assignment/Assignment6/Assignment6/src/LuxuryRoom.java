public class LuxuryRoom extends Room {
    private boolean addBed = false;
    private int addingBed = 250;
    private static double basicPrice = 1200;


    public double getBasicPrice() {
        return basicPrice;
    }

    public void setAddBed(boolean addBed) {
        this.addBed = addBed;
    }

    public static void setBasicPrice(double bP) {
        basicPrice = bP;
    }

    @Override
    public void checkIn(int number) {
        setCheckIn(true);
        setAddBed(number == 3);
    }

    @Override
    public void checkOut() {
        setCheckIn(false);
    }

    @Override
    public double pricePerNight(Day day) {
        return basicPrice * day.getRate() + (addBed ? 1 : 0) * addingBed;
    }

    @Override
    public String toString() {
        String a;
        if(addBed){a = "true";}else {a = "false";}
        return "L "+getNumber()+" "+a;
    }

}
