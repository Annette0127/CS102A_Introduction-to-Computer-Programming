import java.util.Scanner;


public class MyTriangle {
    public static void main(String args[]) {
        System.out.println("Please input three numbers for a, b, c:");
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b,c;
        while (a != -1) {
            b = sc.nextDouble();
            c = sc.nextDouble();
            if (isValid(a, b, c)) {
                System.out.println("The area is " + area(a, b, c));
                System.out.println("The perimeter is " + perimeter(a, b, c));
            } else {
                System.out.println("The input is invalid");
            }
            a = sc.nextDouble();

        }
        System.out.println("Bye~");
    }

    public static double area(double a, double b, double c) {
        double p = perimeter(a, b, c) / 2;
        double ar = Math.pow(p * (p - a) * (p - b) * (p - c), 0.5);
        return ar;
    }

    public static double perimeter(double a, double b, double c) {
        double pe = a + b + c;
        return pe;
    }

    public static boolean isValid(double a, double b, double c) {
        boolean t = false;
        if ((a + b) > c && (b + c) > a && (a + c) > b) {
            t = true;
        }
        return t;
    }

    public static double area(double bottom, double height){
        double area = 1.0/2*bottom*height;
        return area;
    }
    public static double area(double a,double b,int angleOfAandB){
        double area = 1.0/2*a*b*Math.sin(angleOfAandB);
        return area;
    }

}
