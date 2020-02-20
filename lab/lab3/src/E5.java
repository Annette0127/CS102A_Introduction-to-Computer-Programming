import java.util.Scanner;

public class E5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System. in );
        System.out.println("Please input n :");
        double sum = 0;
        float n = sc.nextFloat();
//        float x = (float)n;
        int b = 1;
        float c;
        while(b<=n){
            int a = b%2;
            if(a == 0)c = -1;
                else c = 1;
            sum=sum+4.0*c/(2*b-1);
            b++;
        };
        System.out.printf("The estimation of Pi is %f",sum);

    }
}
