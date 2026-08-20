import java.util.ArrayList;

class Cashier extends Person {
    private static String name;
    private String contact;
    private String cashierId;
    private boolean isAvailable;
    private ArrayList<Cashier> cashierList;

    // Constructor to initialize Cashier details
    public Cashier(String cashierName, String cashierContact, String cashierId)
            throws InvalidNameException, InvalidContactNumberException, InvalidAddressException {
        super(cashierName, "Bank counter", cashierContact);
        this.cashierId = cashierId;
        this.isAvailable = true; // Initially, cashier is available
    }

    public String getCashierId() {

        return cashierId;
    }
    // Setter for cashierList
    public void setCashierList(ArrayList<Cashier> cashierList) {
        this.cashierList = cashierList;
    }

    // Getter for cashierList
    public ArrayList<Cashier> getCashierList() {
        return cashierList;
    }


    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void dealWithCustomer(Customer customer) {
        if (isAvailable) {
            this.isAvailable = false;  // Cashier becomes unavailable once dealing with customer
            System.out.println("Cashier " + super.name + " is serving customer " + customer.getName());
        } else {
            System.out.println("Cashier " + super.name + " is not available.");
        }
    }

    public void freeUp() {
        isAvailable = true;
    }

    public static void openAccount(Customer customer, Account account) {
        customer.addAccount(account);
        System.out.println("Account opened for customer " + customer.getName() + " by cashier.");
    }

    @Override
    public String getName() {
        return super.name;
    }

        // Example method to approve a loan
        public void approveLoan(Loan loan) {
            // Logic to approve loan, for example:
            loan.approveLoan();
            System.out.println("Loan approved by Cashier: " + getName());
            System.out.println("Loan Amount: " + loan.getLoanAmount());
            System.out.println("EMI: " + loan.calculateEMI());
        }

        // Other cashier-related methods like checking availability

        public String getContact() {
            return contact;
        }

}
