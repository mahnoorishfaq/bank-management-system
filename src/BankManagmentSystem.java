import java.util.*;

public class BankManagmentSystem {
    public static ArrayList<Cashier> cashierList = new ArrayList<>();


    public static void main(String[] args) throws InvalidNameException, InvalidContactNumberException, InvalidAddressException {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();  
        ArrayList<Customer> customerList = new ArrayList<>();

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
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    admin.addNewCustomer(customerList);
                    break;

                case 2:
                    admin.viewAllCustomers(customerList);
                    break;

                case 3:
                    if (customerList.isEmpty()) {
                        System.out.println("No customers available. Please add a customer first.");
                    } else {
                        Customer currentCustomer = customerList.remove(0); 
                        System.out.println("\nProcessing customer: " + currentCustomer.getName());
                        simulateCustomerVisit(cashierList, currentCustomer);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the Bank Management System!");
                    exitSystem = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

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
        System.out.println("Opening account for " + customer.getName());
        System.out.println("Select account type: ");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Please select account type: ");
        int accountType = scanner.nextInt();

        if (accountType == 1) {
            System.out.print("Enter initial balance for savings account: ");
            double savingsBalance = scanner.nextDouble();
            System.out.print("Enter interest rate for savings account: ");
            double interestRate = scanner.nextDouble();
            SavingsAccount savingsAccount = new SavingsAccount(savingsBalance, interestRate);

            Cashier.openAccount(customer, savingsAccount);

            Notification notification = new Notification("Your savings account has been successfully opened.");
            notification.sendNotification(customer);

        } else if (accountType == 2) {
            System.out.print("Enter initial balance for current account: ");
            double currentBalance = scanner.nextDouble();
            CurrentAccount currentAccount = new CurrentAccount(currentBalance);

            Cashier.openAccount(customer, currentAccount);

            Notification notification = new Notification("Your current account has been successfully opened.");
            notification.sendNotification(customer);
        } else {
            System.out.println("Invalid account type. Try again.");
        }
    }

    public static void depositMoneyService(Customer customer, Scanner scanner) {
        System.out.print("Enter deposit amount: ");
        double depositAmount = scanner.nextDouble();

        if (customer.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts. Please open an account first.");
            return; 
        }


        Account account = customer.getAccounts().getFirst(); 
        if (customer.getAccounts().size() > 1) {
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            System.out.print("Enter account in which you have to deposit money: ");

            int accountT = scanner.nextInt();

            if (accountT == 1) {
                account = customer.getSavingAccount();
            }
            if (accountT == 2) {
                account = customer.getCurrentAccount();
            }
        }
        account.deposit(depositAmount);

        String accountType = account.getType();
        Notification notification = new Notification("Deposit of " + depositAmount + " made to your " + accountType + " account.");
        notification.sendNotification(customer);
    }


    public static void withdrawMoneyService(Customer customer, Scanner scanner) {
        System.out.print("Enter withdrawal amount: ");
        double withdrawalAmount = scanner.nextDouble();

        if (customer.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts. Please open an account first.");
            return; 
        }
        Account account = customer.getAccounts().getFirst();
        if (customer.getAccounts().size() > 1) {
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            System.out.print("Enter account from which you want to withdraw money: ");

            int accountT = scanner.nextInt();

            if (accountT == 1) {
                account = customer.getSavingAccount();
            }
            if (accountT == 2) {
                account = customer.getCurrentAccount();
            }
        }

        String accountType = account.getType();
        try {
            account.withdraw(withdrawalAmount);
            Notification notification = new Notification("Withdrawal of " + withdrawalAmount + " made from your" + accountType + " account.");
            notification.sendNotification(customer);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void applyForLoanService(Customer customer, Scanner scanner) {
        if (customer.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts. Please open an account first.");
            openAccountService(customer, scanner); 
            return; 
        }
        System.out.print("Enter loan amount: ");
        double loanAmount = scanner.nextDouble();
        System.out.print("Enter interest rate: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Enter tenure (months): ");
        int tenure = scanner.nextInt();
        System.out.println("Do you want to take the loan?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("Select one option : ");
        int decision = scanner.nextInt();

        if (decision == 1) {

            Loan loan = new Loan(loanAmount, interestRate, tenure) {
                @Override
                public double calculateEMI() {
                    double rate = interestRate / (12 * 100); 
                    return (loanAmount * rate * Math.pow(1 + rate, tenure)) / (Math.pow(1 + rate, tenure) - 1);
                }
            };
            LoanAccount loanAccount = new LoanAccount(loan);

            customer.addAccount(loanAccount);
            System.out.println("EMI : " + loan.calculateEMI());
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
                    System.out.println("Remaining loan balance: " + loanAccount.getRemainingBalance());
                } else if (paymentOption == 2) {
                    continueLoanPayments = false;
                } else {
                    System.out.println("Returning without taking the loan.");
                }

                Cashier availableCashier = null;
                for (Cashier cashier : cashierList) {
                    if (cashier.isAvailable()) {
                        availableCashier = cashier;
                        break;
                    }
                }


            }

        }

    }
}
