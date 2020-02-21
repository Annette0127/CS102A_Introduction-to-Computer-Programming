/**
 * Created by zym on 19/4/23
 */
public abstract class Room {
    private static int count = 1;
    private String number;
    private Boolean checkIn = false;
    protected Day day = Day.MONDAY;

    public static int getCount() {
        return count;
    }

    public Room() {
        this.number = "R" + count++;
    }

    public String getNumber() {
        return this.number;
    }

    public boolean getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }

    public abstract void checkIn(int number);

    public abstract void checkOut();

    public abstract double pricePerNight(Day day);

    @Override
    public String toString() {
        return String.format("%s", this.number);
    }
}
