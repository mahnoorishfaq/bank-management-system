class SavingsLoan extends Loan {

    public SavingsLoan(double loanAmount, double interestRate, int tenure) {
        super(loanAmount, interestRate, tenure);
    }

    @Override
    public double calculateEMI() {
        double rate = interestRate / 12 / 100; // Monthly interest rate
        return (loanAmount * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
    }
}
