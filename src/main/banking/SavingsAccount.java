package banking;


public class SavingsAccount extends BankAccount implements Printable, Auditable{
    private static final int MAX_WITHDRAWALS_PER_MONTH = 3;
    private static final double INTEREST_RATE = 0.04;

    private int withdrawalsThisMonth;

    //constructor - must call super() to initialise inherited fields
    public SavingsAccount(String accountId, String ownerName, double initialBalance) {
        super(accountId, ownerName, initialBalance);
        this.withdrawalsThisMonth = 0;
    }

    //no-deposit constructor - delegates to above
    public SavingsAccount(String accountId, String ownerName) {
        this(accountId, ownerName, MIN_BAL); //MIN_BAL inherited from BankAccount
    }

    @Override
    public String getAccountType() { return "SAVINGS"; }

    //Override withdraw() - SavingsAccount has its own rules
    @Override
    public void withdraw(double amount) {
        if(withdrawalsThisMonth >= MAX_WITHDRAWALS_PER_MONTH) {
          System.out.printf("Withdrawal limit reached (%d/month). Try next month.%n", MAX_WITHDRAWALS_PER_MONTH);
          return;
        }
        //parent's withdraw() handles amount validation + MIN_BAL check
        super.withdraw(amount);
        //only increment if the parent actually allowed the withdrawal
        if(amount > 0 && getBalance() >= MIN_BAL) {
            withdrawalsThisMonth++;
            System.out.printf("Withdrawals used this month: %d/%d%n", withdrawalsThisMonth, MAX_WITHDRAWALS_PER_MONTH);
        }
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        logDeposit(amount);
    }

    //new method  - only SavingsAccount has this
    public void applyMonthlyInterest() {
        double interest = getBalance() * (INTEREST_RATE / 12);
        deposit(interest);
        System.out.printf("Interest applied: %2f %s (%.1f%% annual rate)%n",
                interest, CURRENCY, INTEREST_RATE * 100);
    }

    public void resetMonthlyWithdrawals() {
        withdrawalsThisMonth = 0;
        System.out.println("Monthly withdrawal count reset.");
    }

    public int getWithdrawalsThisMonth() { return withdrawalsThisMonth; }

    @Override
    public void printStatement() {
        System.out.println("=== Savings Account Statement ===");
        System.out.println(toString());
        System.out.printf("Withdrawals used: %d/%d | Interest rate: %.1f%%%n",
                withdrawalsThisMonth, MAX_WITHDRAWALS_PER_MONTH, INTEREST_RATE);
    }

    @Override
    public void logTransaction(String action, double amount) {
        System.out.printf("[AUDIT] %s | %s | %.2f %s%n",
                getAccountId(), action, amount, CURRENCY);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Savings | Withdrawals: %d/%d]",
                withdrawalsThisMonth, MAX_WITHDRAWALS_PER_MONTH);
    }
}
