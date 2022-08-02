class ChequingAccount extends Account {
    
    @Override
    public void withdraw(int amount) throws BankException {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            this.createTransaction(-amount);
        } else {
            throw new BankException("You don't have enough money");
        } 
    }       

}
