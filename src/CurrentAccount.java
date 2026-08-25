public class CurrentAccount extends Account {

    public CurrentAccount(double balance) {
        super(balance);  
    }

    @Override
    public void deposit(double amount) {
        balance += amount;  
        System.out.println("Deposited " + amount + " to current account. New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient funds in current account.");
        }
        balance -= amount;  
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
