import java.util.ArrayList;
import java.util.UUID;

abstract class Account {

    private UUID id;
    protected int balance;
    protected ArrayList<Transaction> transactions;

    public Account() {
        this.id = UUID.randomUUID();
        this.balance = 0;
        this.transactions = new ArrayList<Transaction>();
    }
    
    public abstract void withdraw(int amount) throws BankException;

    public void deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            this.createTransaction(amount);
        } else {
            System.out.println("Error");
        }
    }
    
    public int getBalance() {
        return this.balance;
    }

    public UUID getID() {
        return this.id;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }
    
    public void createTransaction(int amount) {
        Transaction transaction = new Transaction(this, amount);
        transactions.add(transaction);
    }

    public void printTransactions() {
        for (Transaction transaction : transactions) {
            transaction.printTransaction();
        }
    }

}



// public abstract void withdraw(int amount) {
//     if (amount > 0 && this.balance >= amount) {
//         this.balance -= amount;
//         this.previousTransaction = -amount;
//     }
// }