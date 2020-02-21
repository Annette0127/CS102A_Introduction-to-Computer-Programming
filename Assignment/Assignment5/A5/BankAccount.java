public class BankAccount {
    private Customer owner;
    private double balance;

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    BankAccount(Customer customer, double balance){
        owner = customer;
        this.balance = balance;
    }
}
