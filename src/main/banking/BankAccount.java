package banking;

public class BankAccount {
    private String accountId;
    private String ownerName;
    private double balance;

    public BankAccount(String accountId, String ownerName, double initialBalance) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

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

        if(amount > balance) {
            System.out.println("Insufficient funds.");
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

    @Override
    public String toString() {
        return String.format("Account[%s  |  Owner: %s  |  Balance: %.2f]",
                accountId, ownerName, balance);
    }
}