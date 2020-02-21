public class A1Q2 {
    public static void main(String args[]){
    double cny = Float.parseFloat(args[0]);
    double hkd=0.00;
    if(cny<=50)
        {System.out.printf("%.2f\n",hkd);}
    else
        {hkd= (cny-50)*1.17;
        System.out.printf("%.2f\n",hkd);}
    }

}
