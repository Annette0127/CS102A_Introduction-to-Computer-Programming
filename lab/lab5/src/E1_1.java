import java.util.Scanner;

public class E1_1 {
    public static void main(String[] args){
        System.out.print("Enter the length of myList1:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] myList1 = new double[n];
        System.out.print("Enter "+ n + " values :");
        for(int i=0;i<n;i++){
            myList1[i] = sc.nextInt();
        }
        double[] myList2 = new double[n];

        for(int j = 0;j<(n-1);j++){
            myList2[n-1] = myList1[0];
            myList2[j] = myList1[j+1];
        }

        System.out.print("myList1:");
        for(int i = 0; i < myList1.length; i++) {
            System.out.print(myList2[i] + "\t");
        }
        System.out.println();
        System.out.print("myList2:");
        for(int i = 0; i < myList2.length; i++) {
            System.out.print(myList1[i] + "\t");
        }
    }
}
