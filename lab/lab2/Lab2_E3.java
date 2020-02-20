import java.util.Scanner;

public class Lab2_E3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the width of a rectangle: ");
        double width = sc.nextDouble();
        System.out.println("Enter the height of a rectangle: ");
        double height = sc.nextDouble();
        System.out.printf("The area is %.2f.\n", width * height);
        System.out.printf("The perimeter is %.2f.\n", 2 * (width + height));
        sc.close();
    }
}
