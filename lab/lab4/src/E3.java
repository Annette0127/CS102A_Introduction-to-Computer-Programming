public class E3 {
    public static void main(String[] args) {
    int year = Integer.parseInt(args[0]);
    int month = Integer.parseInt(args[1]);
    String monthName = "";
    int days = 0;
    boolean isLeapYear = false;
    if (((year%4==0)^(year%100==0))||(year%400 == 0)    ) {
        isLeapYear = true;
    } else {
        isLeapYear = false;
    }
    switch (month) {
        case 1:
            days = 31;
            monthName = "January";
            break;
        case 2:
            if(isLeapYear) {days =29;}
                else{days = 28;}
            monthName = "February";
            break;

        case 3:
            days = 31;
            monthName = "March";
            break;

        case 4:
            days = 30;
            monthName = "April";
            break;

        case 5:
            days = 31;
            monthName = "May";
            break;

        case 6:
            days = 30;
            monthName = "June";
            break;

        case 7:
            days = 31;
            monthName = " July";
            break;

        case 8:
            days = 31;
            monthName = "August";
            break;

        case 9:
            days = 30;
            monthName = "September";
            break;

        case 10:
            days = 31;
            monthName = "October";
            break;

        case 11:
            days = 30;
            monthName = " November";
            break;

        case 12:
            days = 31;
            monthName = "December";
            break;

        default:
            System.out.println("error!!!");
            break;
    }
    System.out.printf("%s of %d has %d days.\n", monthName, year, days);
}}