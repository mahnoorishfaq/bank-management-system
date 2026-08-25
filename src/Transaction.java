import java.util.*;

class Transaction {
    private String transactionId;
    private String type;
    private double amount;
    private int timestamp;

    public Transaction(String transactionId, String type, double amount, int timestamp){
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }
    public void processTransaction(){
        System.out.println("Transaction ID: " + transactionId + ", Type: " + type + ", Amount: " + amount + ", Timestamp: " + timestamp);
    }
}
