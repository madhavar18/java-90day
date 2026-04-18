package banking;

public class BankAccount {
    //STATIC (class-leve, shared across all accounts)
    private static String bankName = "ABC";
    private static int totalAccCreated = 0;
    public static final double MIN_BAL = 500.0;
    public static final String CURRENCY = "INR";

    //INSTANCE (unique to each BankAccount object)
    private final String accountId;
    private String ownerName;
    private double balance;

    //CONSTRUCTOR 1: full details with opening deposit
    public BankAccount(String accountId, String ownerName, double initialBalance) {
        if(initialBalance < MIN_BAL) {
            throw new IllegalArgumentException(
                    "Initial balance must be at least" + MIN_BAL + " " + CURRENCY
            );
        }
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        totalAccCreated++;
    }

    //CONSTRUCTOR 2: no deposit, starts at MIN_BAL
    public BankAccount(String accountId, String ownerName) {
        this(accountId, ownerName, MIN_BAL);
    }

    //STATIC methods (can ONLY access static fields
    public static int getTotalAccCreated() {
        return totalAccCreated;
    }

    public static String getBankName() {
        return bankName;
    }

    public static void setBankName(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        bankName = name;
    }

    //INSTANCE methods
    public void deposit(double amount) {
        if(amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("Deposited %.2f.  New balance%.2f%n", amount, balance);
    }

    public void withdraw(double amount) {
        if(amount <= 0) {
            System.out.print("Deposit amount must be positive.");
            return;
        }

        if(balance - amount < MIN_BAL) {
            System.out.printf("Cannot withdraw: would fall below min %.2f %s%n.", MIN_BAL, CURRENCY);
            return;
        }
        balance -= amount;
        System.out.printf("Withdrew %.2f.  New balance%.2f%n", amount, balance);
    }

    public double getBalance() {
        return balance;
    }
    public String getAccountId() {
        return accountId;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String name) {
        if(name != null && !name.isBlank()) this.ownerName = name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %.2f %s",
                accountId, ownerName, bankName, balance, CURRENCY);
    }
}