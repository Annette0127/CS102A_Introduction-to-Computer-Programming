package sustech.cs102a.lab10;
import java.util.EnumSet;
import java.util.Scanner;

public class Lab10E1 {
    public static void main(String[] args) {
        System.out.print("Your budget: ");
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        if (money < 5588) {
            System.out.print("You don't have sufficient money.");
        } else {
            for (PhoneModel phoneModel : PhoneModel.values()) {
                if (phoneModel.getPrice() <= money) {
                    System.out.printf("%-10s", phoneModel);
                    System.out.printf("%-30s\n", phoneModel.getPrice());

                }
            }
        }


    }
}

