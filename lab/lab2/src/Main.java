import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        //System.out.println("Welcome to CS102A!");
        Scanner sc=new Scanner(System.in);
        float width = 0;
        float hight = 0;
        float area = 0;
        float perimiter=0;
        System.out.println("enter the width of a rectangle:");
        width = sc.nextFloat();
        System.out.println("enter the hight of a rectangle;");
        hight = sc.nextFloat();
        area = width*hight;
        perimiter = width*2+hight*2;
        System.out.printf("The area is %f\n", area);
        System.out.printf("The perimiter is %f\n", perimiter);

        Scanner A=new Scanner(System.in);
        int second = 0;
        int hour = 0;
        int minute = 0;
        int time = 0;
        System.out.println("Enter the number of seconds:");
        time = A.nextInt();
        hour = time/3600;
        minute = (time-3600*hour)/60;
        second = time-(3600*hour)-(60*minute);
        System.out.printf("The equivalent time is %d hours %d minutes %d seconds\n", hour, minute, second);
        A.close();
    }
}

