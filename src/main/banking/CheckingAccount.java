package banking;

public class CheckingAccount extends BankAccount{
    private static final double TRANSACTION_FEE = 20.0;
    private int totalTransactions;
    private double totalFeesCharged;

    public CheckingAccount(String accountId, String ownerName, double initialBalance) {
        super(accountId, ownerName, initialBalance);
        this.totalTransactions = 0;
        this.totalFeesCharged = 0.0;
    }

    public CheckingAccount(String accountId, String ownerName) {
        this(accountId, ownerName, MIN_BAL);
    }

    @Override
    public void withdraw(double amount) {
        double totalDeduction = amount + TRANSACTION_FEE;
        if(getBalance() - totalDeduction < MIN_BAL) {
            System.out.printf("Insufficient funds. Need %.2f (amount) + %.2f (fee) = %.2f %s%n",
                    amount, TRANSACTION_FEE, totalDeduction, CURRENCY);
            return;
        }
        //Manually deduct amount + fee without calling super (different deduction logic)
        super.withdraw(amount);
        super.withdraw(TRANSACTION_FEE);
        totalTransactions++;
        totalFeesCharged += TRANSACTION_FEE;
        System.out.printf("Transaction fee: %.2f %s charged.%n", TRANSACTION_FEE, CURRENCY);
    }

    public void printAccountSummary() {
        System.out.println(toString());
        System.out.printf("Total transactions: %d | Total fees paid: %.2f %s%n",
                totalTransactions, totalFeesCharged, CURRENCY);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Checking | Txns: %d | Fees: %.2f]",
                totalTransactions, totalFeesCharged);
    }
}
