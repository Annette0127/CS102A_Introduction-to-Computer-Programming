import java.util.Scanner;

public class E5 {
    public static void main(String[] args) {
        System.out.println("How many numbers you will input:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++){
            ar[i] = sc.nextInt();
        }
        int i=0;
        for(int m=n-2;m>=0;m--){
            i=0;
            while (i<=m){
                if(ar[i]>ar[i+1]) {
                    int a = ar[i];
                    ar[i] = ar[i + 1];
                    ar[i + 1] = a;
                }
                i++;
            }

        }
        for(int e: ar){
            System.out.print(e +" ");
        }

    }
}
