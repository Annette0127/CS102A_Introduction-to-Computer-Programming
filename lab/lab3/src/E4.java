import java.util.Random;
import java.util.Scanner;
public class E4 {
    public static void main(String[] args){
        Random random = new Random();
        int magicNumber = random.nextInt(10);
        int inputNumber;
        Scanner sc = new Scanner(System. in );
        System.out.println("Please input an Integer in {0,1,2,...,9}:");
        inputNumber = sc.nextInt();
        while(magicNumber != inputNumber) {
            if (magicNumber <= inputNumber)
                System.out.println("Too big!Please try again:");
            else
                System.out.println("Too small!Please try again:");
            inputNumber = sc.nextInt();
            }
        System.out.println("Congratulations!:");
        sc.close();
    }


    }



