import java.util.Scanner;

public class E4 {
    public static void main(String[] args) {
        int[] ar = new int[100];
        System.out.println("Enter the integer between 1 and 100:");
        Scanner sc = new Scanner(System.in);
        int a = -1;

        do{a = sc.nextInt();
        ar[a] += 1 ;
        }while (a !=0);

        for(int i = 0;i<100;i++){
            if(ar[i]!=0){
                if(ar[i]==1) {
                    System.out.println(i + "occurs "+"1" + " time" );
                }
                else {
                    System.out.println(i + "occurs "+ar[i] + " times" );
                }
            }
        }
    }
}

