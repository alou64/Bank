import java.time.LocalDateTime;
import java.util.UUID;

class Transaction {

    private UUID id;
    private Account account;
    private LocalDateTime date;
    private int amount;
    private int balance;

    public Transaction(Account account, int amount) {
        this.id = UUID.randomUUID();
        this.account = account;
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.balance = account.getBalance();
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void printTransaction() {
        String transactionType = amount > 0 ? "Deposit" : "Withdrawal";

        System.out.println("Transaction ID: " + this.id);
        System.out.println("Account ID: " + this.account.getID());
        System.out.println("Date: " + this.date);
        System.out.println(transactionType + ": " + Math.abs(this.amount));
        System.out.println("Balance: " + this.balance);
    }

}
