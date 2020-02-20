import java.util.ArrayList;
import java.util.Scanner;

public class Count {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String[] s = s1.split(s2);
        int count = 0;
        ArrayList<Integer> position = new ArrayList(1);
        boolean a = true;
        int po = 0;
        while (a) {
            position.add(s1.indexOf(s2, po));
            po += s2.length();
            if (po >= s1.length()||s1.indexOf(s2, po)==-1) {
                a = false;
            }
            count++;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Found at index: " + position.get(i));
        }


        System.out.println(s.length);
    }
}
