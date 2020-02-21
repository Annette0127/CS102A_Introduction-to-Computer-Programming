import java.util.*;
import java.util.stream.Collectors;


public class ConcreteHotel implements Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Double> incomes = new ArrayList<>();
    private double basicOrdinaryRoom = 500;
    private double basicLuxuryRoom = 1200;
    private Day currentDay = Day.MONDAY;

    public Day getCurrentDay() {
        return currentDay;
    }

    @Override
    public void addRoom(int type, int count) {
        if (type == 0) {
            for (int i = 0; i < count; i++) {
                rooms.add(new LuxuryRoom());
            }
        } else {
            for (int i = 0; i < count; i++) {
                rooms.add(new OrdinaryRoom());
            }
        }
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void setPrice(int type, double price) {
        if (type == 0) {
            basicLuxuryRoom = price;
            LuxuryRoom.setBasicPrice(price);
        } else {
            basicOrdinaryRoom = price;
            OrdinaryRoom.setBasicPrice(price);
        }
    }

    @Override
    public double getPrice(int type) {
        return type == 0 ? basicLuxuryRoom : basicOrdinaryRoom;
    }

    @Override
    public double getRoomPrice(String number) {
        Room room = rooms.stream().filter(r -> r.getNumber().equals(number)).findFirst().get();
        return room.getCheckIn() ? room.pricePerNight(currentDay) : 0;
    }

    @Override
    public void displayAllRooms() {
        rooms.forEach(r -> System.out.println(r.toString()));
    }

    @Override
    public List<Room> getAllCheckedRooms() {
        return rooms.stream().filter(Room::getCheckIn)
                .sorted(Comparator.comparing(r -> r.getCount())).collect(Collectors.toList());
    }

    @Override
    public void displayEveryDayInfo() {
        for (Day day : Day.values()) System.out.println(day.toString());
    }

    @Override
    public void displayTodayInfo() {
        System.out.println(currentDay.toString());
    }

    @Override
    public void checkIn(int type, int number) {
        Class[] roomType = {LuxuryRoom.class, OrdinaryRoom.class};
        rooms.stream().filter(r -> !r.getCheckIn()).filter(r -> r.getClass().equals(roomType[type]))
                .min(Comparator.comparing(r -> Room.getCount())).get().checkIn(number);

    }

    @Override
    public void checkOut(String... roomNumber) {
        incomes.add(rooms.stream().filter(Room::getCheckIn).mapToDouble(r -> r.pricePerNight(currentDay)).sum());
        Day[] days = Day.values();
        currentDay = days[(currentDay.ordinal() + 1) % 7];
        for (Room room : rooms) {
            for (String roomN : roomNumber) {
                if (room.getNumber().equals(roomN)) {
                    room.checkOut();
                }
            }
        }
    }

    @Override
    public double income() {
        return incomes.get(incomes.size()-1);
    }

    @Override
    public double income(int recentTimes) {
        return incomes.stream().skip(incomes.size() - recentTimes).mapToDouble(r -> r).sum();
    }
}
