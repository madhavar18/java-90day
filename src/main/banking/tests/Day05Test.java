package banking.tests;

import banking.*;
import java.util.List;
import java.util.Optional;

public class Day05Test {
    public static void main(String[] args) {

        BankRepository repo = new BankRepository();

        // TEST 1 — adding accounts
        System.out.println("=== Adding Accounts ===");
        repo.addAccount(new SavingsAccount("SAV001", "Ravi", 10000.0));
        repo.addAccount(new SavingsAccount("SAV002", "Priya", 8000.0));
        repo.addAccount(new CheckingAccount("CHK001", "Amit", 15000.0));
        repo.addAccount(new CheckingAccount("CHK002", "Sneha", 6000.0));
        repo.addAccount(new SavingsAccount("SAV003", "Vikram", 20000.0));

        // TEST 2 — duplicate ID rejected
        System.out.println("\n=== Duplicate ID Test ===");
        try {
            repo.addAccount(new SavingsAccount("SAV001", "Fake", 5000.0));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage()); // expected
        }

        // TEST 3 — O(1) lookup by ID
        System.out.println("\n=== Lookup by ID ===");
        Optional<BankAccount> found = repo.findById("CHK001");
        found.ifPresentOrElse(
                acc -> System.out.println("Found: " + acc),
                () -> System.out.println("Not found.")
        );

        Optional<BankAccount> notFound = repo.findById("GHOST");
        notFound.ifPresentOrElse(
                acc -> System.out.println("Found: " + acc),
                () -> System.out.println("GHOST: not found — Optional handled safely, no NPE")
        );

        // TEST 4 — filter by type
        System.out.println("\n=== Savings Accounts Only ===");
        List<BankAccount> savings = repo.findByType("SAVINGS");
        savings.forEach(System.out::println);

        // TEST 5 — filter by minimum balance
        System.out.println("\n=== Accounts with balance >= 10000 ===");
        repo.findByMinBalance(10000.0).forEach(System.out::println);

        // TEST 6 — deposit and withdraw through repo
        System.out.println("\n=== Transactions ===");
        repo.deposit("SAV001", 5000.0);
        repo.withdraw("CHK001", 2000.0);

        // TEST 7 — owner name uniqueness check (Set in action)
        System.out.println("\n=== Owner Name Check ===");
        System.out.println("Is 'Ravi' registered? " + repo.isOwnerNameRegistered("Ravi"));
        System.out.println("Is 'ravi' registered? " + repo.isOwnerNameRegistered("ravi")); // case-insensitive
        System.out.println("Is 'Ghost' registered? " + repo.isOwnerNameRegistered("Ghost"));

        // TEST 8 — remove account
        System.out.println("\n=== Remove Account ===");
        repo.removeAccount("SAV003");
        System.out.println("After removal, total: " + repo.getTotalAccounts()); // 4

        // TEST 9 — full summary
        repo.printSummary();
    }
}
