public abstract class Person {
    protected String name;
    protected String address;
    protected String contactNumber;


    public Person(String name, String address, String contactNumber)
            throws InvalidNameException, InvalidAddressException, InvalidContactNumberException {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Name cannot be null or empty");
        }
        if (address == null || address.isEmpty()) {
            throw new InvalidAddressException("Address cannot be null or empty");
        }
        if (contactNumber == null || !contactNumber.matches("\\d{11,}")) {
            throw new InvalidContactNumberException("Invalid Contact Number. It must be 10 digits");
        }

        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public abstract String getName();
    public void updateDetails(String name, String address, String contactNumber)
            throws InvalidNameException, InvalidAddressException, InvalidContactNumberException {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Name cannot be null or empty");
        }
        if (address == null || address.isEmpty()) {
            throw new InvalidAddressException("Address cannot be null or empty");
        }
        if (contactNumber == null || !contactNumber.matches("\\d{10}")) {
            throw new InvalidContactNumberException("Invalid Contact Number. It must be 10 digits");
        }

        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public String getDetails() {
        return "Name: " + name + ", Address: " + address + ", Contact Number: " + contactNumber;
    }
}
