import java.util.Scanner;

public class E3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the length of array:");
        int n = sc.nextInt();
        int[] ar1 = new int[n];
        int[] ar2 = new int[n];
        System.out.print("Enter the 1st integer array of size" + n + ":");
        for(int i=0;i<n;i++){
            ar1[i] = sc.nextInt();
        }
        System.out.print("Enter the 2st integer array of size" + n + ":");
        for(int i=0;i<n;i++){
            ar2[i] = sc.nextInt();
        }
        boolean a = true;
        for(int i = 0;i < n;i++){

            if (ar1[i] != ar2[i]){
                a = false;
                break;
            }
            else {a = true;}

            }
        if(a==false){System.out.println("The two arrays have different value.");}
        else{System.out.println("The two arrays have the same value.");}
        }

    }

