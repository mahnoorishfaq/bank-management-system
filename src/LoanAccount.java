public class LoanAccount extends Account {
    private Loan loan;
    private double remainingBalance;

    // Constructor to initialize Loan Account
    public LoanAccount(Loan loan) {
        super(0);  // Initial balance is 0 for loan account
        this.loan = loan;
        this.remainingBalance = loan.getLoanAmount(); // Initialize remaining balance with loan amount
    }

    // Method to make payment on the loan
    public void makePayment(double paymentAmount) {
        if (paymentAmount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }

        if (paymentAmount > remainingBalance) {
            System.out.println("Payment exceeds remaining loan balance. Updating payment to remaining balance.");
            paymentAmount = remainingBalance;
        }

        remainingBalance -= paymentAmount;
        System.out.println("Payment of " + paymentAmount + " made. Remaining balance: " + remainingBalance);
    }

    // Method to get remaining loan balance
    public double getRemainingBalance() {
        return remainingBalance;
    }


    @Override
    public void deposit(double amount) {
        makePayment(amount);
    }

    @Override
    public void withdraw(double amount) throws Exception {
        throw new Exception("Withdrawals are not allowed from loan accounts.");
    }

    @Override
    public String getType() {
        return "Loan Account";
    }

    public double getBalance() {
        return balance;
    }
}
