import java.text.DecimalFormat;
import java.util.ArrayList;

public class BankService {
    private final static DecimalFormat sp = new DecimalFormat(",##0.00");
    private double availableCash;
    private ArrayList<BankAccount> accounts = new ArrayList<>();

    public BankAccount getAccount(Customer customer) {
        for (BankAccount ba : accounts) {
            if (ba.getOwner().getSocialSecurityNumber().equals(customer.getSocialSecurityNumber())) {
                return ba;
            }
        }
        return null;
    }

    public BankAccount getAccount(String ssn) {
        for (BankAccount ba : accounts) {
            if (ba.getOwner().getSocialSecurityNumber().equals(ssn)) {
                return ba;
            }
        }
        return null;
    }

    public void checkAccountBalance(String ssn) {
        boolean f = true;
        for (BankAccount ba : accounts) {
            if (ba.getOwner().getSocialSecurityNumber().equals(ssn)) {
                if (ba.getOwner().getGender() == Gender.MALE) {
                    System.out.printf("Mr. %s, your account balance: %.2f \u00A5\n", ba.getOwner().getLastName(), ba.getBalance());
                } else if (ba.getOwner().getGender() == Gender.FEMALE) {
                    System.out.printf("Ms. %s, your account balance: %.2f \u00A5\n", ba.getOwner().getLastName(), ba.getBalance());
                }
                f = false;
            }
        }
        if (f) System.out.println("No account found");
    }

    public void createAccount(String firstName, String lastName, char gender, String ssn) {
        boolean onlyOne = true;
        double exBalance = 0;
        for (BankAccount ba : accounts) {
            if (ba.getOwner().getSocialSecurityNumber().equals(ssn)) {
                onlyOne = false;
                exBalance = ba.getBalance();
            }
        }
        Gender a;
        if (gender == 'm'||gender=='M') {
            a = Gender.MALE;
            Customer customer = new Customer(firstName, lastName, a, ssn);
            BankAccount bankAccount = new BankAccount(customer, 0.0);
            if (Customer.checkName(customer.formatName(firstName)) && Customer.checkName(customer.formatName(lastName))) {
                if (Customer.checkSSN(ssn) && onlyOne) {
                    accounts.add(accounts.size(), bankAccount);
                    System.out.println("Congrats, " + "Mr. " + customer.getLastName() + "! Your account is created successfully.");
                } else if (!onlyOne) {
                    System.out.printf("Mr. %s, you already have an account with a balance %.2f\u00A5.\n", customer.getLastName(), exBalance);
                } else {
                    System.out.println("Invalid social security number");
                }
            } else {
                System.out.println("Invalid name");
            }
        } else if (gender == 'f'||gender=='F') {
            a = Gender.FEMALE;
            Customer customer = new Customer(firstName, lastName, a, ssn);
            BankAccount bankAccount = new BankAccount(customer, 0.0);

            if (Customer.checkName(customer.getFirstName()) && Customer.checkName(customer.getLastName())) {
                if (Customer.checkSSN(ssn) && onlyOne) {
                    accounts.add(accounts.size(), bankAccount);
                    System.out.println("Congrats, " + "Ms. " + customer.getLastName() + "! Your account is created successfully.");
                } else if (!onlyOne) {
                    System.out.printf("Ms. %s, you already have an account with a balance %.2f\u00A5.\n", customer.getLastName(), exBalance);
                } else {
                    System.out.println("Invalid social security number");
                }
            } else {
                System.out.println("Invalid name");
            }
        } else {
            System.out.println("Invalid input for gender");
        }
    }

//    public void createAccount(String firstName, String lastName, char gender, String ssn) {
//        if (!Customer.checkName(firstName) || !Customer.checkName(lastName)) {
//            System.out.println("Invalid name");
//            return;
//        } else if (gender != 'm' && gender != 'f') {
//            System.out.println("Invalid input for gender");
//            return;
//        } else if (!Customer.checkSSN(ssn)) {
//            System.out.println("Invalid social security number");
//            return;
//        }
//        Gender g = gender == 'm' ? Gender.MALE : Gender.FEMALE;
//        double exBalance = accounts.stream().filter(a -> a.getOwner().getSocialSecurityNumber().equals(ssn))
//                .map(BankAccount::getBalance).findFirst().orElse(-1);
//        if (exBalance < 0) {
//            accounts.add(accounts.size(), new BankAccount(new Customer(firstName, lastName, g, ssn), 0));
//            System.out.println("Congrats, " + toString(g) + lastName + "! Your account is created successfully.");
//        } else {
//            System.out.printf(toString(g) + "%s, you already have an account with a balance %s\u00A5.\n",
//                    lastName, spt.format(exBalance));
//        }
//    }

    public void makeDeposit(String ssn, double amount) {
        double balance = 0;
        boolean ex = false;
        if (!Customer.checkSSN(ssn)) {
            System.out.println("Invalid social security number");
            return;
        }
        if (amount >= 0) {
            //
            for (BankAccount ba : accounts) {
                if (ba.getOwner().getSocialSecurityNumber().equals(ssn)) {
                    balance = ba.getBalance();
                    ex = true;
                    ba.setBalance(balance + amount);
                }
            }
            if (ex) {
                System.out.printf("Original balance: %.2f \u00A5\nBalance after deposit: %.2f \u00A5\n", balance, balance + amount);
            } else {
                System.out.println("No account found");
            }
//            double balance = accounts.stream().filter(a -> a.getOwner().getSocialSecurityNumber().equals(ssn))
//                    .peek(a -> a.setBalance(a.getBalance() + amount)).map(BankAccount::getBalance).findFirst()
//                    .orElse(-1);
//            if (balance < 0) {
//                System.out.println("No account found");
//            } else {
//                System.out.printf("Original balance : %s\u00A5\nBalance after deposit: %s\u00A5\n",
//                spt.format(balance), spt.format(balance + amount));
//            }
        } else {
            System.out.println("Invalid amount");
        }
    }

    public void withdraw(String ssn, double amount) {
        double balance = 0;
        boolean ex = false;
        if (availableCash >= amount) {
            if (amount >= 0) {
                for (BankAccount ba : accounts) {
                    if (ba.getOwner().getSocialSecurityNumber().equals(ssn)) {
                        ex = true;
                        balance = ba.getBalance();
                        if (amount <= balance) {
                            balance = ba.getBalance();
                            availableCash -= amount;
                            ba.setBalance(balance - amount);
                            System.out.printf(" the original balance is %.2f \u00A5\nbalance after withdrawal: %.2f \u00A5\n", balance, balance - amount);
                        } else {
                            System.out.println("No enough balance or cash");
                        }
                    }
                }
                if (!ex) {
                    System.out.println("No account found");
                }

            } else System.out.println("Invalid amount");
        } else System.out.println("No enough balance or cash");
    }


    public static void calculateMonthlyPayment(double loanAmount, int years) {
        double monthRate;
        double YearRate;
        if (loanAmount < 0) {
            System.out.println("invalid loan");
            return;
        }
        if (years <= 0) {
            System.out.println("invalid loan");
            return;
        }
        if (1 <= years && years <= 2) {
            monthRate = 4.35 / 12;
            YearRate = 4.35;
        } else if (years <= 5) {
            monthRate = 4.75 / 12;
            YearRate = 4.75;
        } else {
            monthRate = 4.9 / 12;
            YearRate = 4.9;
        }
        int monthNumber = 12 * years;
        double t = Math.pow(1 + monthRate * 0.01, monthNumber);
        double monthlyPayment = loanAmount * monthRate * 0.01 * t / (t - 1);
        System.out.printf("\nCalculation result:\nLoan amount: %s \u00A5\nYearly interest rate: %.2f %%\nNumber of installments (months): %d\nMonthly payment: %s \u00A5\nTotal interest: %s \u00A5\n", sp.format(loanAmount), YearRate, monthNumber, sp.format(monthlyPayment), sp.format(monthlyPayment * monthNumber - loanAmount));
    }

    BankService() {
        availableCash = 100000;
        System.out.printf("Initializing bank service: no accounts yet, %.2f \u00A5 cash available\n", availableCash);
        accounts.clear();
    }

    public static String toString(Gender gender) {
        return gender == Gender.MALE ? "Mr. " : "Ms. ";
    }
}