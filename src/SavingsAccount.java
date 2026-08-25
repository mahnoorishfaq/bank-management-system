public class SavingsAccount extends Account {
    private double interestRate;  
    
    public SavingsAccount(double balance, double interestRate) {
        super(balance);  
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;  
        System.out.println("Deposited " + amount + " to savings account. New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient funds in savings account.");
        }
        balance -= amount; 
        System.out.println("Withdrew " + amount + " from savings account. New balance: " + balance);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        balance += balance * interestRate / 100;  
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
