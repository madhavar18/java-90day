package banking.tests;

import banking.*;

public class Day04Test {
    public static void main(String[] args) {

        // TEST 1 — abstract class: BankAccount cannot be instantiated
        // new BankAccount("X", "Y", 1000) — COMPILE ERROR. Abstract = no direct objects.

        // TEST 2 — dynamic dispatch in action
        System.out.println("=== Dynamic Dispatch ===");
        BankAccount acc1 = new SavingsAccount("SAV001", "Ravi", 10000.0);
        BankAccount acc2 = new CheckingAccount("CHK001", "Priya", 10000.0);
        acc1.withdraw(1000.0); // SavingsAccount's withdraw — limit tracking
        acc2.withdraw(1000.0); // CheckingAccount's withdraw — fee charged
        // Same method call. Completely different behaviour. No if-statement.

        // TEST 3 — polymorphic loop: one loop, multiple behaviours
        System.out.println("\n=== Polymorphic Loop ===");
        BankAccount[] accounts = {
                new SavingsAccount("SAV002", "Amit", 5000.0),
                new CheckingAccount("CHK002", "Sneha", 5000.0),
                new SavingsAccount("SAV003", "Vikram", 8000.0)
        };
        for (BankAccount acc : accounts) {
            acc.printAccountInfo(); // calls getAccountType() — each returns its own type
            acc.withdraw(500.0);    // each uses its own withdraw() rules
        }

        // TEST 4 — interface reference: code to the contract, not the implementation
        System.out.println("\n=== Interface Polymorphism ===");
        Printable printable = new SavingsAccount("SAV004", "Divya", 12000.0);
        printable.printStatement(); // only Printable methods visible through this reference

        // TEST 5 — Auditable interface default methods
        System.out.println("\n=== Auditable Default Methods ===");
        Auditable auditable = new CheckingAccount("CHK003", "Kiran", 7000.0);
        auditable.logDeposit(2000.0);    // default method from interface
        auditable.logWithdrawal(500.0);  // default method from interface

        // TEST 6 — instanceof + casting to access subclass-specific methods
        System.out.println("\n=== Casting ===");
        for (BankAccount acc : accounts) {
            if (acc instanceof SavingsAccount sa) {
                sa.applyMonthlyInterest(); // only SavingsAccount has this
            }
        }

        // TEST 7 — getAccountType() from abstract method — each class returns its own
        System.out.println("\n=== Account Types ===");
        for (BankAccount acc : accounts) {
            System.out.println(acc.getAccountId() + " is type: " + acc.getAccountType());
        }
    }
}