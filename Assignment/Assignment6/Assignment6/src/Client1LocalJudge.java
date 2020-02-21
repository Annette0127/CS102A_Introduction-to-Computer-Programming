import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Client1 Local Judge.<br>
 * You can get 6.67 points for each passed Test, and the total grade of this assignment is 120
 * Your actual grade is an integer, which is calculated by Math.round(grade)
 *
 * @author zhuym
 * @version 1.0
 * @since <pre>05/04/2019</pre>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Client1LocalJudge {
    private static Hotel hotel = new ConcreteHotel();
    private static ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
    private static PrintStream cacheStream = new PrintStream(output);

    private static final String lineSeparator = System.lineSeparator();

    @Before
    public void before() throws Exception {
        System.setOut(cacheStream);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: task001_getRoomInfo()
     * You do not need to understand what the method is, just pass the test is ok.
     */
    @Test
    public void task001_getRoomInfo() {
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
        String msg = output.toString();
        output.reset();

        assertTrue(lineSeparator + msg, msg.isEmpty());

    }

    /**
     * Method: task002_getSubClassInfo
     * You do not need to understand what the method is, just pass the test is ok.
     */
    @Test
    public void task002_getSubClassInfo() {
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
        String msg = output.toString();
        output.reset();

        assertTrue(lineSeparator + msg, msg.isEmpty());
    }

    /**
     * Method: task01_addLuxuryRoom()
     */
    @Test
    public void testTask01_addLuxuryRoom() throws Exception {
        Room room = new LuxuryRoom();
        hotel.addRoom(room);
        assertEquals("L R1 false", room.toString());

    }

    /**
     * Method: task02_addOrdinaryRoom()
     */
    @Test
    public void testTask02_addOrdinaryRoom() throws Exception {
        Room room = new OrdinaryRoom();
        hotel.addRoom(room);
        assertEquals("O R2 0", room.toString());
    }

    /**
     * Method: task03_addMoreRoom()
     */
    @Test
    public void testTask03_addMoreRoom() throws Exception {
        hotel.addRoom(0, 5);
        hotel.addRoom(1, 10);
        hotel.addRoom(0, 2);

        hotel.displayAllRooms();
        String msg = output.toString();
        output.reset();
        assertEquals("L R1 false" + lineSeparator +
                "O R2 0" + lineSeparator +
                "L R3 false" + lineSeparator +
                "L R4 false" + lineSeparator +
                "L R5 false" + lineSeparator +
                "L R6 false" + lineSeparator +
                "L R7 false" + lineSeparator +
                "O R8 0" + lineSeparator +
                "O R9 0" + lineSeparator +
                "O R10 0" + lineSeparator +
                "O R11 0" + lineSeparator +
                "O R12 0" + lineSeparator +
                "O R13 0" + lineSeparator +
                "O R14 0" + lineSeparator +
                "O R15 0" + lineSeparator +
                "O R16 0" + lineSeparator +
                "O R17 0" + lineSeparator +
                "L R18 false" + lineSeparator +
                "L R19 false" + lineSeparator, msg);

    }

    /**
     * Method: task04_getTwoTypeRoomPrice()
     */
    @Test
    public void testTask04_getTwoTypeRoomPrice() throws Exception {
        assertEquals("1200 500" + lineSeparator,
                String.format("%d %d" + lineSeparator, (int) hotel.getPrice(0), (int) hotel.getPrice(1)));
    }

    /**
     * Method: task05_setTwoTypeRoomPrice()
     */
    @Test
    public void testTask05_setTwoTypeRoomPrice() throws Exception {
        hotel.setPrice(0, 1300);
        hotel.setPrice(1, 600);
        assertEquals("1300 600" + lineSeparator,
                String.format("%d %d" + lineSeparator, (int) hotel.getPrice(0), (int) hotel.getPrice(1)));
    }

    /**
     * Method: task06_displayEveryDayInfo()
     */
    @Test
    public void testTask06_displayEveryDayInfo() throws Exception {
        hotel.displayEveryDayInfo();
        String msg = output.toString();
        output.reset();
        assertEquals("Day{name='MON', rate=0.8, gift='Fruits'}" + lineSeparator +
                "Day{name='TUE', rate=0.75, gift='Drinks'}" + lineSeparator +
                "Day{name='WED', rate=0.71, gift='GYM CARD'}" + lineSeparator +
                "Day{name='THU', rate=0.68, gift='Fruits'}" + lineSeparator +
                "Day{name='FRI', rate=1.0, gift='GYM CARD'}" + lineSeparator +
                "Day{name='SAT', rate=1.0, gift='HOT SPRINGS'}" + lineSeparator +
                "Day{name='SUN', rate=0.95, gift='SWIMMING'}" + lineSeparator, msg);
    }

    /**
     * Method: task07_checkInForFirstDay()
     */
    @Test
    public void testTask07_checkInForFirstDay() throws Exception {
        hotel.checkIn(0, 2);
        hotel.checkIn(0, 3);
        hotel.checkIn(1, 2);
        hotel.checkIn(1, 0);
        hotel.checkIn(0, 3);
        hotel.checkIn(1, 0);
        for (Room room : hotel.getAllCheckedRooms()) {
            System.out.printf("%s ", room.getNumber());
        }
        System.out.println();
        String msg = output.toString();
        output.reset();
        assertEquals("R1 R2 R3 R4 R8 R9 " + lineSeparator, msg);
    }

    /**
     * Method: task08_checkOutForSecondDay()
     */
    @Test
    public void testTask08_checkOutForSecondDay() throws Exception {
        hotel.checkOut("R1", "R2", "R8");
        for (Room room : hotel.getAllCheckedRooms()) {
            System.out.printf("%s ", room.getNumber());
        }
        System.out.println();
        String msg = output.toString();
        output.reset();
        assertEquals("R3 R4 R9 " + lineSeparator, msg);
    }

    /**
     * Method: task09_displayYesterdayIncome()
     */
    @Test
    public void testTask09_displayYesterdayIncome() throws Exception {
        assertEquals("5420", Integer.toString((int) hotel.income()));
    }

    /**
     * Method: task10_displayTodayInfo()
     */
    @Test
    public void testTask10_displayTodayInfo() throws Exception {
        hotel.displayTodayInfo();
        String msg = output.toString();
        output.reset();
        assertEquals("Day{name='TUE', rate=0.75, gift='Drinks'}" + lineSeparator, msg);
    }

    /**
     * Method: task11_displayTotalPriceForOneRoom()
     */
    @Test
    public void testTask11_displayTotalPriceForOneRoom() throws Exception {
        assertEquals("1225", Integer.toString((int) hotel.getRoomPrice("R3")));
    }

    /**
     * Method: task12_operateForSeveralDays()
     */
    @Test
    public void testTask12_operateForSeveralDays() throws Exception {
        File file = new File("passengerInfo.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
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

    /**
     * Method: task13_displayAllCheckedRoomNumber()
     */
    @Test
    public void testTask13_displayAllCheckedRoomNumber() throws Exception {
        for (Room room : hotel.getAllCheckedRooms()) {
            System.out.print(room.getNumber() + " ");
        }
        System.out.println();
        String msg = output.toString();
        output.reset();
        assertEquals("R2 R4 R5 R9 R11 R19 " + lineSeparator, msg);
    }

    /**
     * Method: task14_displayYesterdayIncome()
     */
    @Test
    public void testTask14_displayYesterdayIncome() throws Exception {
        assertEquals("9082", Integer.toString((int) hotel.income()));
    }

    /**
     * Method: task15_displayTodayInfo()
     */
    @Test
    public void testTask15_displayTodayInfo() throws Exception {
        hotel.displayTodayInfo();
        String msg = output.toString();
        output.reset();
    }

    /**
     * Method: task16_displayRecentDaysIncome()
     */
    @Test
    public void testTask16_displayRecentDaysIncome() throws Exception {
        assertEquals("53024", Integer.toString((int) hotel.income(5)));
    }

} 
