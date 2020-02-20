import java.util.Scanner;

public class Lab2_E4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of seconds: ");
        int input = sc.nextInt();
        int hour = input / 3600;
        int minute = (input % 3600) / 60;
        int second = input % 60;
        System.out.printf("The equivalent time is %d hours %d minutes and %d seconds.\n", hour, minute, second);
        sc.close();
    }
}
