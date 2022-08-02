import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Queue;

class SavingsAccount extends Account {

    private Queue<Transaction> withdrawals;
    private int monthlyWithdrawLimit;

    public SavingsAccount(int monthlyWithdrawLimit) {
        super();
        this.withdrawals = new ArrayDeque<Transaction>();
        this.monthlyWithdrawLimit = monthlyWithdrawLimit;
    }
    
    @Override
    public void withdraw(int amount) throws BankException {

        // Check if balance greater than withdraw amount
        if (amount > 0 && this.balance >= amount) {

            // Check monthly withdraw limit & update withdrawals
            if (withdrawals.size() == monthlyWithdrawLimit) {
                Transaction oldestWithdraw = this.withdrawals.peek();
                LocalDateTime currentDate = LocalDateTime.now();
                
                // 
                if (oldestWithdraw.getDate().getMonth() == currentDate.getMonth()) {
                    throw new BankException("Exceeded monthly withdraw limit");
                }

                // Remove all withdrawals not from current month
                this.updateWithdrawals();
            }

            this.balance -= amount;
            this.createTransaction(-amount);
            this.withdrawals.add(this.transactions.get(this.transactions.size() - 1));
        } else {
            throw new BankException("You don't have enough money");
        }   
    }
    
    public void updateWithdrawals() {
        Transaction oldestWithdraw = this.withdrawals.peek();
        LocalDateTime currentDate = LocalDateTime.now();

        while (!this.withdrawals.isEmpty() && oldestWithdraw.getDate().getMonth() != currentDate.getMonth()) {
            this.withdrawals.poll();
        }
    }

    // TODO: change withdrawlimit

}
