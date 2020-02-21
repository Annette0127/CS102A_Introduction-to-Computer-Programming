public class A1Q3 {
    public static void main(String[] args){
        int le = Integer.parseInt(args[0]);
        int la = Integer.parseInt(args[1]);
        int as = Integer.parseInt(args[2]);
        int pr = Integer.parseInt(args[3]);
        int fi = Integer.parseInt(args[4]);
        double gr= (double)(le+la)*0.1+(as+fi)*0.3+pr*0.2;
        System.out.printf("%.2f\n",gr);
        if(gr>=80)
            {System.out.println("A");}
        else if(gr>=50)
            {System.out.println("B");}
        else
            {System.out.println("C");}

    }
}
