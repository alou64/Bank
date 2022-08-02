import java.util.UUID;

class Customer {
    
    private UUID id;
    private String name;
    private ChequingAccount chequingAccount;
    private SavingsAccount savingsAccount;
    private Account receiveAccount;
    private Account activeAccount;

    public Customer(String name, int savingsWithdrawLimit) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.chequingAccount = new ChequingAccount();
        this.savingsAccount = new SavingsAccount(savingsWithdrawLimit);
        this.receiveAccount = this.chequingAccount;
        this.activeAccount = this.chequingAccount;
    }

    public void showActiveAccount() {
        if (this.activeAccount.getID() == this.chequingAccount.getID()) {
            System.out.println("Chequing account is active.");
        } else {
            System.out.println("Savings account is active.");
        }
    }

    public void switchActiveAccount() {
        this.activeAccount = this.activeAccount.getID() == this.chequingAccount.getID() ? this.savingsAccount : this.chequingAccount;
        this.showActiveAccount();
    }

    public void withdraw(int amount) throws BankException {
        try 
        {
            this.activeAccount.withdraw(amount);
        }
        catch(BankException withdrawException) 
        {
            System.out.println(withdrawException.getMessage());
        }

        this.showActiveAccountBalance();
    }

    public void deposit(int amount) {
        this.activeAccount.deposit(amount);
        System.out.println("Deposited $" + amount + ".");
        this.showActiveAccountBalance();
    }

    public void showReceiveAccount() {
        if (this.receiveAccount.getID() == this.chequingAccount.getID()) {
            System.out.println("Currently receiving transfers to chequing account.");
        } else {
            System.out.println("Currently receiving transfers to savings account.");
        }
    }

    public void switchReceiveAccount() {
        this.receiveAccount = this.receiveAccount.getID() == this.chequingAccount.getID() ? this.savingsAccount : this.chequingAccount;
        this.showReceiveAccount();
    }

    public void receiveMoney(int amount) {
        this.receiveAccount.deposit(amount);
    }

    public void sendMoney(Customer recipient, int amount) {
        // Withdraw money first
        try
        {
            this.withdraw(amount);
        } 
        catch(BankException withdrawExcetion)
        {
            System.out.println(withdrawExcetion.getMessage());
        }

        // Deposit to recipient account
        recipient.receiveMoney(amount);

        System.out.println("Successfuly sent $" + amount + " to "+ recipient.name + ".");
        this.showActiveAccountBalance();
    }

    public void showActiveAccountBalance() {
        System.out.println("Active account balance is: $" + this.activeAccount.getBalance() + ".");
    }

    public void showTotalBalance() {
        int chequingBalance = this.chequingAccount.getBalance();
        int savingsBalance = this.savingsAccount.getBalance();

        System.out.println("Chequing account balance is: $" + chequingBalance + ".");
        System.out.println("Savings account balance is: $" + savingsBalance + ".");
        System.out.println("Total balance is: $" + (chequingBalance + savingsBalance) + ".");
    }

    // TODO: print transactions

    public void showActiveAccountTransactions() {
        this.activeAccount.printTransactions();
    }

}
