import java.util.Scanner;

public class Main {
        private static Scanner sc = new Scanner(System.in);
        private static BankService bankService = new BankService();

        public static void main(String[] args) {
            printAvailableServices();
            int command = 0;
            while((command = Integer.parseInt(sc.nextLine())) != 0) {
                switch(command) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        checkBalance();
                        break;
                    case 3:
                        makeDeposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        getMortgageInfo();
                        break;
                    case 0:
                        sc.close();
                        System.exit(0);
                    default:
                        System.err.println("invalid input, try again");
                        break;
                }
                System.out.println();
                printAvailableServices();
            }
        }

        public static void printAvailableServices() {
            System.out.println("- Press 1 to create an account");
            System.out.println("- Press 2 to check account balance");
            System.out.println("- Press 3 to make a deposit");
            System.out.println("- Press 4 to withdraw cash");
            System.out.println("- Press 5 to get advices on house mortgage loan");
            System.out.println("- Press 0 to exit");
        }

        public static void createAccount() {
            System.out.println("Please provide the following information:");
            System.out.print("Your first name: ");
            String firstName = sc.nextLine().trim();
            System.out.print("Your last name: ");
            String lastName = sc.nextLine().trim();
            System.out.print("Your gender (type 'm' for Male and 'f' for Female): ");
            char gender = Character.toLowerCase(sc.nextLine().trim().charAt(0));
            System.out.print("Your social security number: ");
            String ssn = sc.nextLine().trim();
            bankService.createAccount(firstName, lastName, gender, ssn);
        }

        public static void checkBalance() {
            System.out.print("Please provide your social security number: ");
            String ssn = sc.nextLine().trim();
            bankService.checkAccountBalance(ssn);
        }

        public static void makeDeposit() {
            System.out.print("Please provide your social security number: ");
            String ssn = sc.nextLine().trim();
            System.out.print("Deposit amount (\u00A5): ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            bankService.makeDeposit(ssn, amount);
        }

        public static void withdraw() {
            System.out.print("Please provide your social security number: ");
            String ssn = sc.nextLine().trim();
            System.out.print("Withdraw amount (\u00A5): ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            bankService.withdraw(ssn, amount);
        }

        public static void getMortgageInfo() {
            System.out.print("Loan amount (\u00A5): ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Loan term (years): ");
            int years = Integer.parseInt(sc.nextLine().trim());
            BankService.calculateMonthlyPayment(amount, years);
        }


}
