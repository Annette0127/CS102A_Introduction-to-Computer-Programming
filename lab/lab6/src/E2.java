import java.util.Scanner;

public class E2 {
    public static void main(String[] args) {
        System.out.println("Please input two numbers for bottom and height:");
        Scanner sc = new Scanner(System.in);
        double bottom = sc.nextDouble();
        double height = sc.nextDouble();
        double area = MyTriangle.area(bottom, height);
        System.out.printf("The area is %d\n" ,area);
        System.out.println("Please input two numbers for a and b:");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        System.out.println("Please input a number in (0,180) for angle (angle is a float variable)");
        int x = sc.nextInt();
        double area2 = MyTriangle.area(a, b, x);
        System.out.printf("The area is %d\n",area2);

    }

   }
