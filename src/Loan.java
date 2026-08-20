public abstract class Loan {
    protected double loanAmount;
    protected double interestRate;
    protected int tenure;
    protected boolean isApproved;

    // Constructor to initialize loan details
    public Loan(double loanAmount, double interestRate, int tenure) {
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.isApproved = false;
    }

    public void approveLoan() {
        this.isApproved = true;
        System.out.println("Loan of amount " + loanAmount + " is approved.");
    }

    public abstract double calculateEMI();

    public String getLoanDetails() {
        return "Loan Amount: " + loanAmount + ", Interest Rate (%): " + interestRate + ", Tenure: " + tenure + " months";
    }

    public Double getLoanAmount() {
        return this.loanAmount;

    }
}
