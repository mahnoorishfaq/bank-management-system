import java.util.Date;

public class Notification {
    private String message;
    private Date timestamp;

    public Notification(String message) {
        this.message = message;
        this.timestamp = new Date(); // current timestamp
    }

    public void sendNotification(Customer customer) {
        customer.addNotification(message);
        System.out.println("Notification sent: " + message + ", Time: " + timestamp);
    }
}
