import java.util.Scanner;
public class A1Q5 {
    public static void main(String[] args){
        int sum ;
        do{Scanner ac = new Scanner(System. in );
            System.out.print("Enter the first number: ");
            int a = ac.nextInt();
            Scanner bc = new Scanner(System. in );
            System.out.print("Enter the second number: ");
            int b = bc.nextInt();
            sum = a+b;
            if(sum != 100){
                System.out.println("Sum of two numbers is "+sum);
                System.out.println("Sum does not equal 100, loop repeats");}
        }while (sum != 100);
        System.out.println("Sum of two numbers is "+sum);
        System.out.println("Sum equals 100, loop terminates");
    }
}
