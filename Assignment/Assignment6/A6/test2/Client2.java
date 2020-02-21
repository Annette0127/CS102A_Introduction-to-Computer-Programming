
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client2 {
    private static Hotel hotel = new ConcreteHotel();

    public static void main(String[] args) {

        task001_getRoomInfo();

        task002_getSubClassInfo();

        task01_addLuxuryRoom();

        task02_addOrdinaryRoom();

        task03_addMoreRoom();

        task04_getTwoTypeRoomPrice();

        task05_setTwoTypeRoomPrice();

        task06_displayEveryDayInfo();

        task07_checkInForFirstDay();

        task08_checkOutForSecondDay();

        task09_displayYesterdayIncome();

        task10_displayTodayInfo();

        task11_displayTotalPriceForOneRoom();

        task12_operateForSeveralDays();

        task13_displayAllCheckedRoomNumber();

        task14_displayYesterdayIncome();

        task15_displayTodayInfo();

        task16_displayRecentDaysIncome();

    }


    private static void task001_getRoomInfo() {
        String[] requiredMethods = {
                "public java.lang.String Room.toString()",
                "public java.lang.String Room.getNumber()",
                "public abstract void Room.checkIn(int)",
                "public abstract void Room.checkOut()",
                "public void Room.setCheckIn(boolean)",
                "public boolean Room.getCheckIn()"
        };
        String[] requiredFields = {
                "private static int Room.count",
                "private java.lang.String Room.number",
                "private java.lang.Boolean Room.checkIn"
        };
        try {
            Class room = Class.forName("Room");
            List<String> declaredFields = Arrays.stream(room.getDeclaredFields()).map(Field::toString).collect(Collectors.toList());
            List<String> declaredMethods = Arrays.stream(room.getDeclaredMethods()).map(Method::toString).collect(Collectors.toList());

            for (String field : requiredFields) {
                if (!declaredFields.contains(field)) {
                    System.out.println(field + " is missing");
                }
            }

            for (String method : requiredMethods) {
                if (!declaredMethods.contains(method)) {
                    System.out.println(method + "is missing");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

    }

    private static void task002_getSubClassInfo() {
        String[] requiredFieldsInLuxuryRoom = {
                "private boolean LuxuryRoom.addBed"
        };

        String[] requiredFieldsInOrdinaryRoom = {
                "private int OrdinaryRoom.breakfastCount"
        };
        String[] requiredMethodsInLuxuryRoom = {
                "public java.lang.String LuxuryRoom.toString()",
                "public void LuxuryRoom.checkIn(int)",
                "public void LuxuryRoom.checkOut()"
        };

        String[] requiredMethodsInOrdinaryRoom = {
                "public java.lang.String OrdinaryRoom.toString()",
                "public void OrdinaryRoom.checkIn(int)",
                "public void OrdinaryRoom.checkOut()"
        };


        try {
            Class luxuryRoom = Class.forName("LuxuryRoom");
            Class ordinaryRoom = Class.forName("OrdinaryRoom");
            String superName = luxuryRoom.getSuperclass().getName();
            if (!superName.equals("Room")) {
                System.out.println("Invalid Super Class of LuxuryRoom");
            }
            superName = ordinaryRoom.getSuperclass().getName();
            if (!superName.equals("Room")) {
                System.out.println("Invalid Super Class of OrdinaryRoom");
            }

            List<String> declareFieldsInLuxury = Arrays.stream(luxuryRoom.getDeclaredFields()).map(Field::toString).collect(Collectors.toList());
            List<String> declareFieldsInOrdinary = Arrays.stream(ordinaryRoom.getDeclaredFields()).map(Field::toString).collect(Collectors.toList());
            List<String> declareMethodsInLuxury = Arrays.stream(luxuryRoom.getDeclaredMethods()).map(Method::toString).collect(Collectors.toList());
            List<String> declareMethodsInOrdinary = Arrays.stream(ordinaryRoom.getDeclaredMethods()).map(Method::toString).collect(Collectors.toList());

            for (String field : requiredFieldsInLuxuryRoom) {
                if (!declareFieldsInLuxury.contains(field)) {
                    System.out.println(field + " is missing");
                }
            }

            for (String field : requiredFieldsInOrdinaryRoom) {
                if (!declareFieldsInOrdinary.contains(field)) {
                    System.out.println(field + " is missing");
                }
            }
            for (String method : requiredMethodsInLuxuryRoom) {
                if (!declareMethodsInLuxury.contains(method)) {
                    System.out.println(method + " is missing");
                }
            }
            for (String method : requiredMethodsInOrdinaryRoom) {
                if (!declareMethodsInOrdinary.contains(method)) {
                    System.out.println(method + " is missing");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }


    private static void task01_addLuxuryRoom() {
        Room room = new LuxuryRoom();
        System.out.println(room);
        hotel.addRoom(room);
    }

    private static void task02_addOrdinaryRoom() {
        Room room = new OrdinaryRoom();
        System.out.println(room);
        hotel.addRoom(room);
    }

    private static void task03_addMoreRoom() {
        hotel.addRoom(0, 2);
        hotel.addRoom(1, 2);
        hotel.addRoom(0, 2);
        hotel.addRoom(1, 2);
        hotel.addRoom(0, 2);
        hotel.addRoom(1, 2);
        hotel.addRoom(0, 2);
        hotel.addRoom(1, 2);
        hotel.addRoom(0, 2);
        hotel.addRoom(1, 2);
        hotel.displayAllRooms();
    }

    private static void task04_getTwoTypeRoomPrice() {
        System.out.printf("%d %d\n", (int)hotel.getPrice(0), (int)hotel.getPrice(1));
    }

    private static void task05_setTwoTypeRoomPrice() {
        hotel.setPrice(0, 2005);
        hotel.setPrice(1, 999);
        task04_getTwoTypeRoomPrice();
    }

    private static void task06_displayEveryDayInfo() {
        hotel.displayEveryDayInfo();
    }

    private static void task07_checkInForFirstDay() {
        hotel.checkIn(1, 2);
        hotel.checkIn(1, 0);
        hotel.checkIn(0, 2);
        hotel.checkIn(0, 3);
        hotel.checkIn(1, 1);
        for (Room room : hotel.getAllCheckedRooms()) {
            System.out.printf("%s ", room.getNumber());
        }
        System.out.println();
    }

    private static void task08_checkOutForSecondDay() {
        hotel.checkOut("R2", "R6", "R3");
        for (Room room : hotel.getAllCheckedRooms()) {
            System.out.printf("%s ", room.getNumber());
        }
        System.out.println();
    }

    private static void task09_displayYesterdayIncome() {
        System.out.println((int)hotel.income());
    }

    private static void task10_displayTodayInfo() {
        hotel.displayTodayInfo();
    }

    private static void task11_displayTotalPriceForOneRoom() {
        System.out.println((int)hotel.getRoomPrice("R3"));
    }


    private static void task12_operateForSeveralDays() {
        File file = new File("passengerInfo2.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {

                if (line.charAt(0) != 'R') {
                    //check in for one day
                    String[] checkInInfo = line.split(",");
                    hotel.checkIn(Integer.parseInt(checkInInfo[0]), Integer.parseInt(checkInInfo[1]));

                } else {
                    //check out for one day
                    String[] checkOutInfo = line.split(",");
                    hotel.checkOut(checkOutInfo);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }


    }
    private static void task13_displayAllCheckedRoomNumber() {
        for(Room room : hotel.getAllCheckedRooms()){
            System.out.print(room.getNumber()+" ");
        }
//        hotel.getAllCheckedRooms().forEach(room -> System.out.print(room.getNumber()+" "));
        System.out.println();
    }


    private static void task14_displayYesterdayIncome() {
        System.out.println((int) hotel.income());
    }

    private static void task15_displayTodayInfo() {
        hotel.displayTodayInfo();
    }


    private static void task16_displayRecentDaysIncome() {
        System.out.println((int) hotel.income(6));
    }


}