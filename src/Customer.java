import java.util.ArrayList;
import java.util.List;

class Customer extends Person {
    private String name;
    private String address;
    private String contact;
    private ArrayList<Account> accounts = new ArrayList<>();
    private static List<String> notifications = new ArrayList<>();

    public Customer(String name, String address, String contactNumber)
            throws InvalidNameException, InvalidAddressException, InvalidContactNumberException {
        super(name, address, contactNumber);
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void viewAccounts() {
        System.out.println("Accounts for " + name + ": ");
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public static void addNotification(String message) {
        notifications.add(message);
    }

    public String getName() {
        return super.name;
    }

        // Getter for accounts
        public ArrayList<Account> getAccounts() {
            return accounts;
        }

        public Account getSavingAccount(){
            for (Account account: accounts){
                if(account instanceof SavingsAccount){
                    return account;
                }
            }
            return null;
        }

        public Account getCurrentAccount(){
            for (Account account: accounts){
                if(account instanceof CurrentAccount){
                    return account;
                }
            }
            return null;
        }

        // Method to add an account to the customer
        public String getAddress() {
            return super.address;
        }
        public String getContactNumber() {
            return super.contactNumber;
    }
    @Override
    public String toString() {
        return "Name: " + name + " | Contact: " + contact + " | Address: " + address;
    }
}
