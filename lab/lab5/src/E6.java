import java.util.Random;
import java.util.Scanner;

public class E6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = 0, n = 0, sum = 0;
        Random random = new Random();
        System.out.print("Enter how many numbers: ");
        int num = sc.nextInt();
        int[] ar = new int[num];
        for (int i = 1; i < num; i++) {
            ar[i] = random.nextInt(10000);
            sum += (double) ar[i];}
//        System.out.println("Enter "+num+" numbers: ");

//        for(int i = 0;i<num;i++){
//            ar[i] = sc.nextInt();
//        }
            long start = System.currentTimeMillis();

            for (int e : ar) {
                sum += e;
            }
            double av = (double) sum / num;
            for (int j = 0; j < num; j++) {
                for (int k = 0; k < num; k++) {
                    if (ar[k] > av && ar[j] > av && k != j) {
                        m++;
//                  System.out.print(ar[i]+"*"+ar[j]);
                    } else if (ar[k] <= av && ar[j] <= av) {
                        continue;
                    } else {
                        if (((ar[k] + ar[j]) / 2.0) > av && k != j) {
                            n++;
//                       System.out.print(ar[i]+"?"+ar[j]);
                        }
                    }
                }

            }
            System.out.println("average=" + av);
            System.out.println("The number of these couple is " + (m / 2 + n / 2));
            long finish = System.currentTimeMillis();
            System.out.printf("Your program using %.4f seconds", (finish - start) / 1000d);
        }
}

