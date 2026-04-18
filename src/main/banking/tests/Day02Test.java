package banking.tests;
import banking.BankAccount;
public class Day02Test {
    public static void main(String[] args) {

        // TEST 1 — static fields exist before any object is created
        System.out.println("Bank: " + BankAccount.getBankName());
        System.out.println("Accounts: " + BankAccount.getTotalAccCreated()); // 0

        // TEST 2 — both constructors work
        BankAccount acc1 = new BankAccount("ACC001", "Ravi", 10000.0);
        BankAccount acc2 = new BankAccount("ACC002", "Priya"); // starts at MIN_BALANCE
        System.out.println("Total: " + BankAccount.getTotalAccCreated()); // 2
        System.out.println(acc1);
        System.out.println(acc2);

        // TEST 3 — static change affects ALL objects
        BankAccount.setBankName("United Java Bank");
        System.out.println(acc1); // new bank name
        System.out.println(acc2); // also new bank name — same shared field

        // TEST 4 — final: no setter, accountId locked forever
        System.out.println("ID: " + acc1.getAccountId());

        // TEST 5 — withdraw enforces MIN_BALANCE
        acc1.deposit(2000.0);
        acc1.withdraw(11600.0); // leaves 400 — refused
        acc1.withdraw(11000.0); // leaves 1000 — allowed

        // TEST 6 — constructor rejects balance below minimum
        try {
            BankAccount bad = new BankAccount("ACC003", "Ghost", 100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        System.out.println("Total still: " + BankAccount.getTotalAccCreated()); // still 2
    }
}
