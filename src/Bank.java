class Bank {

    public static void main(String[] args) throws BankException {
       Customer alou = new Customer("Alou", 2);
       alou.deposit(10);
       System.out.println("-----------");
       alou.showTotalBalance();
       System.out.println("-----------");
       alou.showActiveAccountTransactions();
       System.out.println("-----------");
       alou.withdraw(30);
    }

}