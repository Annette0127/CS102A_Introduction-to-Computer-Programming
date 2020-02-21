public class A1Q1 {
    public static void main(String[] args){
        float radius = Float.parseFloat(args[0]);
        float Parameter = radius*2*3.14f;
        float S = 3.14f*radius*radius;
        System.out.printf("%.2f\n",Parameter);
        System.out.printf("%.2f\n",S);
    }
}
