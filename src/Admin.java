import java.util.*;

public class Admin {
    public void addNewCustomer(ArrayList<Customer> customers) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter customer name: ");
            String customerName = scanner.nextLine();
            if (customerName.isEmpty() || customerName.matches(".*\\d.*")) {
                throw new InvalidNameException("Invalid name. Name cannot be empty or contain digits.");
            }

            System.out.println("Enter customer contact: ");
            String customerContact = scanner.nextLine();
            if (customerContact.isEmpty() || !customerContact.matches("\\d{11,}")) {
                throw new InvalidContactNumberException("Invalid contact number. Must be 11 digits.");
            }

            System.out.println("Enter customer address: ");
            String customerAddress = scanner.nextLine();
            if (customerAddress.isEmpty()) {
                throw new InvalidAddressException("Address cannot be empty.");
            }

            // Create and add new customer
            Customer newCustomer = new Customer(customerName, customerAddress, customerContact);
            customers.add(newCustomer);
            System.out.println("Customer " + customerName + " has been added successfully!");

        } catch (InvalidNameException | InvalidContactNumberException | InvalidAddressException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewAllCustomers(ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Customer list is empty. Please add a customer first.");
        } else {
            System.out.println("Displaying all customers:");
            for (Customer customer : customers) {
                System.out.println(customer.getName() + " | Contact: " + customer.getContactNumber() + " | Address: " + customer.getAddress());
            }
        }
    }
}
