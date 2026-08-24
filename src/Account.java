public abstract class Account {

    public static final String SAVING_ACCOUNT = "saving";
    public static final String CURRENT_ACCOUNT = "current";
    protected double balance;  

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
    public void withdraw(double amount) throws Exception {
        if (balance < amount) {
            throw new Exception("Insufficient balance");
        }
        this.balance -= amount;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "Balance: " + balance;
    }

    public double getBalance() {
        return balance;
    }
}
