public class CurrentAccount extends Account {

    // Constructor to initialize CurrentAccount
    public CurrentAccount(double balance) {
        super(balance);  // Call superclass constructor to initialize balance
    }

    // Method to deposit money into current account
    @Override
    public void deposit(double amount) {
        balance += amount;  // Add the amount to the current balance
        System.out.println("Deposited " + amount + " to current account. New balance: " + balance);
    }

    // Method to withdraw money from current account
    @Override
    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient funds in current account.");
        }
        balance -= amount;  // Deduct the amount from balance
        System.out.println("Withdrew " + amount + " from current account. New balance: " + balance);
    }

    @Override
    public String getType() {
        return CURRENT_ACCOUNT;
    }

    @Override
    public String toString() {
        return "Current Account - Balance: " + balance;
    }

}
