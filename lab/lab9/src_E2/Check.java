import jdk.internal.util.xml.impl.Input;

import java.util.Scanner;

public class Check {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        while (true) {
            if (s.equals("quit")) {
                break;
            }
            System.out.printf("Type a string (\"quit\" to finish):");
            s = sc.nextLine();
            char temp;
            boolean is = true;
            if (s.length() % 2 == 0) {
                for (int i = 0; i <= (s.length() / 2); i++) {
                    if (s.charAt(i) != s.charAt(s.length() - i - 1))
                    {
                        is = false;
                        break;
                    }
                }
            } else {
                for (int i = 0; i <= ((s.length() - 1) / 2); i++) {
                    if (s.charAt(i) != s.charAt(s.length() - i - 1))
                    {
                        is = false;
                        break;
                    }
                }
            }
            if (is) {
                System.out.println(s + " is a palidorme");
            } else {
                System.out.println(s + " is not a palidorme");
            }
        }

    }
}
