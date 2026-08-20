import java.util.*;

public class BankManagmentSystem {
    public static ArrayList<Cashier> cashierList = new ArrayList<>();


    public static void main(String[] args) throws InvalidNameException, InvalidContactNumberException, InvalidAddressException {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();  // Admin object for managing customers
        ArrayList<Customer> customerList = new ArrayList<>();

        // Step 1: Define 3 predefined cashiers
        cashierList.add(new Cashier("Mahnoor", "03011223344", "001"));
        cashierList.add(new Cashier("Eman", "03122334455", "002"));
        cashierList.add(new Cashier("Ali", "03233445566", "003"));


        boolean exitSystem = false;

        while (!exitSystem) {
            System.out.println("\nWelcome to Bank Management System");
            System.out.println("1. Add New Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Serve Current Customer");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Admin adds new customers one by one
                    admin.addNewCustomer(customerList);
                    break;

                case 2:
                    // View all customers
                    admin.viewAllCustomers(customerList);
                    break;

                case 3:
                    // Serve one customer at a time
                    if (customerList.isEmpty()) {
                        System.out.println("No customers available. Please add a customer first.");
                    } else {
                        Customer currentCustomer = customerList.remove(0); // Serve first customer
                        System.out.println("\nProcessing customer: " + currentCustomer.getName());
                        simulateCustomerVisit(cashierList, currentCustomer);
                    }
                    break;

                case 4:
                    // Exit the system
                    System.out.println("Thank you for using the Bank Management System!");
                    exitSystem = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Simulate customer visits and services
    public static void simulateCustomerVisit(ArrayList<Cashier> cashierList, Customer customer) {
        Scanner scanner = new Scanner(System.in);
        boolean isServed = false;

        for (Cashier cashier : cashierList) {
            if (cashier.isAvailable()) {
                cashier.dealWithCustomer(customer);
                isServed = true;
                break;
            }
        }

        if (!isServed) {
            System.out.println("Sorry, " + customer.getName() + ", all cashiers are busy. Please wait.");
        } else {
            System.out.println("Welcome " + customer.getName() + "! How can we assist you today?");
            boolean continueTransaction = true;

            while (continueTransaction) {
                System.out.println("1. Open Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Apply for Loan");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        openAccountService(customer, scanner);
                        break;
                    case 2:
                        depositMoneyService(customer, scanner);
                        break;
                    case 3:
                        withdrawMoneyService(customer, scanner);
                        break;
                    case 4:
                        applyForLoanService(customer, scanner);
                        break;
                    case 5:
                        continueTransaction = false;
                        System.out.println("Thank you! Returning to main menu.");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    // Existing helper methods (openAccountService, depositMoneyService, withdrawMoneyService, applyForLoanService)
    public static void openAccountService(Customer customer, Scanner scanner) {
        // Step 8: Customer account opening service
        System.out.println("Opening account for " + customer.getName());
        System.out.println("Select account type: ");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Please select account type: ");
        int accountType = scanner.nextInt();

        if (accountType == 1) {
            // Savings Account
            System.out.print("Enter initial balance for savings account: ");
            double savingsBalance = scanner.nextDouble();
            System.out.print("Enter interest rate for savings account: ");
            double interestRate = scanner.nextDouble();
            SavingsAccount savingsAccount = new SavingsAccount(savingsBalance, interestRate);

            // Step 9: Cashier opens account
            Cashier.openAccount(customer, savingsAccount);

            // Send notification
            Notification notification = new Notification("Your savings account has been successfully opened.");
            notification.sendNotification(customer);

        } else if (accountType == 2) {
            // Current Account
            System.out.print("Enter initial balance for current account: ");
            double currentBalance = scanner.nextDouble();
            CurrentAccount currentAccount = new CurrentAccount(currentBalance);

            // Step 9: Cashier opens account
            Cashier.openAccount(customer, currentAccount);

            // Send notification
            Notification notification = new Notification("Your current account has been successfully opened.");
            notification.sendNotification(customer);
        } else {
            System.out.println("Invalid account type. Try again.");
        }
    }

    // Method to deposit money into an account
    public static void depositMoneyService(Customer customer, Scanner scanner) {
        // Step 10: Customer deposit service
        System.out.print("Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();

        // Check if the customer has any accounts
        if (customer.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts. Please open an account first.");
            return; // Exit the method early since no accounts exist
        }


        Account account = customer.getAccounts().getFirst(); // Getting the first account
        if (customer.getAccounts().size() > 1) {
            // input from user
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            System.out.print("Enter account in which you have to deposit money: ");

            int accountT = scanner.nextInt();

            // saving
            if (accountT == 1) {
                account = customer.getSavingAccount();
            }
            // current
            if (accountT == 2) {
                account = customer.getCurrentAccount();
            }
        }
        // Assuming the customer has at least one account (the first account)
        account.deposit(depositAmount);

        String accountType = account.getType();
        // Send notification
        Notification notification = new Notification("Deposit of " + depositAmount + " made to your " + accountType + " account.");
        notification.sendNotification(customer);
    }


    // Method to withdraw money from an account
    public static void withdrawMoneyService(Customer customer, Scanner scanner) {
        // Step 11: Customer withdraw service
        System.out.print("Enter withdrawal amount: ");
        double withdrawalAmount = scanner.nextDouble();

        // Check if the customer has any accounts
        if (customer.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts. Please open an account first.");
            return; // Exit the method early since no accounts exist
        }
        // Assuming the customer has a savings account to withdraw from
        Account account = customer.getAccounts().getFirst();// Getting the first account
        if (customer.getAccounts().size() > 1) {
            // input from user
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            System.out.print("Enter account from which you want to withdraw money: ");

            int accountT = scanner.nextInt();

            // saving
            if (accountT == 1) {
                account = customer.getSavingAccount();
            }
            // current
            if (accountT == 2) {
                account = customer.getCurrentAccount();
            }
        }

        String accountType = account.getType();
        try {
            account.withdraw(withdrawalAmount);
            // Send notification
            Notification notification = new Notification("Withdrawal of " + withdrawalAmount + " made from your" + accountType + " account.");
            notification.sendNotification(customer);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to apply for a loan
    public static void applyForLoanService(Customer customer, Scanner scanner) {
        // Check if the customer has any accounts
        if (customer.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts. Please open an account first.");
            openAccountService(customer, scanner); // Prompt the customer to open an account
            return; // Exit after account creation
        }
        // Step 12: Apply for loan service
        System.out.print("Enter loan amount: ");
        double loanAmount = scanner.nextDouble();
        System.out.print("Enter interest rate: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Enter tenure (months): ");
        int tenure = scanner.nextInt();
        // Ask customer if they want to take the loan or return
        System.out.println("Do you want to take the loan?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("Select one option : ");
        int decision = scanner.nextInt();

        if (decision == 1) {

            // Create the loan object
            Loan loan = new Loan(loanAmount, interestRate, tenure) {
                @Override
                public double calculateEMI() {
                    double rate = interestRate / (12 * 100); // Monthly interest rate
                    return (loanAmount * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
                }
            };
            // Create LoanAccount
            LoanAccount loanAccount = new LoanAccount(loan);

            // Add the loan account to the customer
            customer.addAccount(loanAccount);
            System.out.println("EMI : " + loan.calculateEMI());
            // Process loan payment (if they make any payments)
            boolean continueLoanPayments = true;
            while (continueLoanPayments) {
                System.out.println("Would you want to return the loan?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Select one option : ");
                int paymentOption = scanner.nextInt();

                if (paymentOption == 1) {
                    System.out.print("Enter return loan amount: ");
                    double paymentAmount = scanner.nextDouble();
                    loanAccount.makePayment(paymentAmount);
                    // Show remaining loan balance
                    System.out.println("Remaining loan balance: " + loanAccount.getRemainingBalance());
                } else if (paymentOption == 2) {
                    continueLoanPayments = false;
                } else {
                    System.out.println("Returning without taking the loan.");
                }

                // Find an available cashier
                Cashier availableCashier = null;
                for (Cashier cashier : cashierList) {
                    if (cashier.isAvailable()) {
                        availableCashier = cashier;
                        break;
                    }
                }

//            // If a cashier is available, approve the loan
//            if (availableCashier != null) {
//                availableCashier.approveLoan(loan);  // Call approveLoan on the available cashier
//            } else {
//                System.out.println("Sorry, no cashiers are available to approve the loan right now.");
//            }
//
//            // Send notification
//            if (loan.isApproved) {
//                Notification notification = new Notification("Your loan has been approved.");
//                notification.sendNotification(customer);
//            } else {
//                Notification pendingNotification = new Notification("Your loan has not been approved yet. Please wait.");
//                pendingNotification.sendNotification(customer);
            }

        }

    }
}
