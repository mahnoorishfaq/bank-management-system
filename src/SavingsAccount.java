public class SavingsAccount extends Account {
    private double interestRate;  // Interest rate for savings account

    // Constructor to initialize SavingsAccount
    public SavingsAccount(double balance, double interestRate) {
        super(balance);  // Call superclass constructor to initialize balance
        this.interestRate = interestRate;
    }

    // Method to deposit money into savings account
    @Override
    public void deposit(double amount) {
        balance += amount;  // Add the amount to the current balance
        System.out.println("Deposited " + amount + " to savings account. New balance: " + balance);
    }

    // Method to withdraw money from savings account
    @Override
    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient funds in savings account.");
        }
        balance -= amount;  // Deduct the amount from balance
        System.out.println("Withdrew " + amount + " from savings account. New balance: " + balance);
    }

    // Getter for interest rate
    public double getInterestRate() {
        return interestRate;
    }

    // Method to apply interest to the account balance
    public void applyInterest() {
        balance += balance * interestRate / 100;  // Add interest to balance
        System.out.println("Interest applied to savings account. New balance: " + balance);
    }

    @Override
    public String getType() {
        return SAVING_ACCOUNT;
    }

    @Override
    public String toString() {
        return "Savings Account - Balance: " + balance + ", Interest Rate: " + interestRate + "%";
    }
}
