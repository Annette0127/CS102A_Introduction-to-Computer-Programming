/**
 * Created by zym on 19/4/23
 *
 * @author Yueming
 */

import java.util.List;

public interface Hotel {

    /**
     * In the method, we need to create a kind of {@code type} rooms for {@code count} times
     *
     * @param type  the test cases for {@code type} only have two values: 0 and 1 <br>
     *
     *              {@code type==0} represent the LuxuryRoom<br>
     *              {@code type==1} represent the OrdinaryRoom<br>
     *
     * @param count how many room we want to add
     */
    public void addRoom(int type, int count);

    /**
     * @param room can be an instance of LuxuryRoom or OrdinaryRoom
     */
    public void addRoom(Room room);


    /**
     * @param type  the test cases for {@code type} only have two values: 0 and 1 <br>
     *              {@code type==0}  represent the LuxuryRoom<br>
     *              {@code type==1}  represent the OrdinaryRoom<br>
     * @param price the new basic price of the current {@code type} of room
     */
    public void setPrice(int type, double price);

    /**
     * @param type the test cases for {@code type} only have two values 0 and 1<br>
     *             {@code type==0}  represent the LuxuryRoom<br>
     *             {@code type==1}  represent the OrdinaryRoom<br>
     * @return the basic price of the current {@code type} of room
     */
    public double getPrice(int type);

    /**
     * How to calculate total price for checked room?<br>
     *<ul>
     *     <li>Luxury Room<br>
     *         Basic price of Luxury Room * the rate of price in current day + the price of add bed (if need)
     *     </li>
     *     <li>Ordinary Room<br>
     *         Basic price of Ordinary Room * the rate of price in current day + the price of breakfast * the number of breakfast.
     *      </li>
     *</ul>
     * @param number the {@code number} of room
     * @return If the current status of the room is unchecked, return 0,
     * or else return the total price of the room for current day.
     */
    public double getRoomPrice(String number);

    /**
     * invoke toString method in the Room class to display all rooms
     */
    public void displayAllRooms();

    /**
     * Put all checked rooms from rooms into a List
     *
     * @return a list which contains all checked rooms by ascending order of room number
     */
    public List<Room> getAllCheckedRooms();


    /**
     * invoke the toString method for all instances in enum class Day in default order
     */
    public void displayEveryDayInfo();

    /**
     * invoke the toString method for one time in enum class Day to print today information
     */
    public void displayTodayInfo();

    /**
     * The process of checkIn including
     * <ol>
     *      <li> Which room can be checked?
     *          Find all unchecked rooms of specific {@code type}. <br>
     *          In all unchecked room, find the minimum room number. <br>
     *     </li>
     *      <li>change the checkIn field of the room to be true</li>
     * </ol>
     *
     * @param type   the test cases for {@code type} only have two values 0 and 1 <br>
     *
     *               {@code type==0} represent the LuxuryRoom <br>
     *               {@code type==1} represent the OrdinaryRoom <br>
     *
     * @param number <ol>
     *                   <li>if {@code type==0}, the test cases of {@code number} only have two values 2 and 3 <br>
     *                       {@code number==2} represent there are two people checked in the room <br>
     *                       {@code number==3} represent there are three people checked in the room, an additional bed is needed <br>
     *                  <li>if {@code type==1}, the test cases of {@code number} only have three values 0,1 and 2
     *                       which means how many breakfast reserved</li>
     *               </ol>
     */
    public void checkIn(int type, int number);

    /**
     * The process of check out, including<br>
     * <ul>
     *      <li>Calculate the total incomes for all checked rooms</li>
     *      <li>change the enum type day to the next day</li>
     *      <li>change the checkIn field to be false for all rooms which number is in parameters</li>
     * </ul>
     *
     * @param roomNumber arrays of room numbers that would be checked out today
     */
    public void checkOut(String... roomNumber);

    /**
     * The first step of doing {@link #checkOut(String...)} is to calculate the total incomes,
     * here we needs the result of the calculation.
     *
     * @return The total income of Yesterday
     */
    public double income();

    /**
     * The total income of recent times.
     *
     * @param recentTimes For example, if {@code recentTimes==2},
     *                    we needs all incomes of yesterday and the day before yesterday
     * @return the total income in recent times
     */
    public double income(int recentTimes);

   




}

