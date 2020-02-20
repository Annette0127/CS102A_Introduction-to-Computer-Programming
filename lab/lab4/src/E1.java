import java.util.Scanner;

public class E1 {
    public  static void main(String[] args){
        Scanner sc = new Scanner(System. in );
        System.out.println("Please input n :");
        double sum = 0;
        float n = sc.nextFloat();
//        float x = (float)n;
        float c;
        for(int b = 1;b<=n;b++){
            int a = b%2;
            if(a == 0)c = -1;
            else c = 1;
            sum=sum+4.0*c/(2*b-1);
        };
        System.out.printf("The estimation of Pi is %f",sum);

    }
}
