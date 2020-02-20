public class DaysOfYearMonth {

    public static void main(String[] args) {
        int year = Integer.parseInt(args[0]);
        int month = Integer.parseInt(args[1]);
        String monthName = "";
        int days = 0;
        boolean isLeapYear = false;
        if (    ) {
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

            case 3:

            case 4:

            case 5:

            case 6:

            case 7:

            case 8:

            case 9:

            case 10:

            case 11:

            case 12:

            default:
                System.out.println("error!!!");
                break;
        }
        System.out.printf("%s of %d has %d days.\n", monthName, year, days);
    }
}


