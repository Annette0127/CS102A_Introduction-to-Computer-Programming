import java.util.Scanner;

public class Remove {
    public static void main(String[] args) {
        System.out.printf("Please type a string:");
        StringBuilder re = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (s.equals("")) {
            System.out.println("Empty string, exit...");
        } else {
            for (int i = 0; i < s.length(); i++) {
                boolean notsame = true;
                if(s.charAt(i)==' '){continue;}
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        notsame = false;
                    }
                }if(notsame){
                    re.append(s.charAt(i));}
            }
            System.out.println("After removing repeating chars: " + re);
        }
    }
}

