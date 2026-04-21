package banking;

import java.util.*;

public class BankRepository {
    // Hashmap: O(1) lookup by account ID - the primary store
    private final Map<String, BankAccount> accountsById = new HashMap<>();

    // Set: O(1) check for duplicate owner names - prevents accidental duplicates
    private final Set<String> registeredOwnerNames = new HashSet<>();

    // List: maintains insertion order for "recent accounts" display
    private final List<BankAccount> accountsByCreationOrder = new ArrayList<>();

    // CREATE

    public void addAccount(BankAccount account) {
        String id = account.getAccountId();

        if(accountsById.containsKey(id)) {
            throw new IllegalArgumentException("Account ID already exists: "+ id);
        }

        accountsById.put(id, account);
        registeredOwnerNames.add(account.getOwnerName().toLowerCase());
        accountsByCreationOrder.add(account);

        System.out.printf("Account %s added. Toral accounts: %d%n",
                id, accountsById.size());
    }

    // READ

    // Optional: explicitly represents "might not exist" - no null suprises
    public Optional<BankAccount> findById(String accountId) {
        return Optional.ofNullable(accountsById.get(accountId));
    }

    public List<BankAccount> findByType(String type) {
        List<BankAccount> result = new ArrayList<>();
        for(BankAccount acc : accountsByCreationOrder) {
            if(acc.getAccountType().equalsIgnoreCase(type)) {
                result.add(acc);
            }
        }
        return result;
    }

    public List<BankAccount> findByMinBalance(double minBalance) {
        List<BankAccount> result = new ArrayList<>();
        for(BankAccount acc : accountsByCreationOrder) {
            if(acc.getBalance() >= minBalance) {
                result.add(acc);
            }
        }
        return result;
    }

    // Returns unmodifiable view - callers can read but cannot modify the intenal list
    public List<BankAccount> getAllAccounts() {
        return Collections.unmodifiableList(accountsByCreationOrder);
    }

    public boolean isOwnerNameRegistered(String ownerName) {
        return registeredOwnerNames.contains(ownerName.toLowerCase());
    }

    public int getTotalAccounts() {
        return accountsById.size();
    }

    // UPDATE

    public void deposit(String accountId, double amount) {
        BankAccount acc = getOrThrow(accountId);
        acc.withdraw(amount);
    }

    public void withdraw(String accountId, double amount) {
        BankAccount acc = getOrThrow(accountId);
        acc.withdraw(amount);
    }

    // DELETE

    public boolean removeAccount(String accountId) {
        BankAccount removed = accountsById.remove(accountId);
        if(removed == null) return false;
        accountsByCreationOrder.remove(removed);
        registeredOwnerNames.remove(removed.getOwnerName().toLowerCase());
        System.out.printf("Account %s removed.%n", accountId);
        return true;
    }

    // STATS

    public void printSummary() {
        System.out.println("\n=== Bank Summary ===");
        System.out.println("Bank: " + BankAccount.getBankName());
        System.out.printf("Total accounts: %d%n", accountsById.size());

        double totalDeposits = 0.0;
        for(BankAccount acc : accountsByCreationOrder) {
            totalDeposits += acc.getBalance();
            System.out.printf(" %s%n", acc);
        }
        System.out.printf("Total funds held: %.2f %s%n", totalDeposits, BankAccount.CURRENCY);
    }


    // PRIVATE HELPERS

    private BankAccount getOrThrow(String accountId) {
        return findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found: "+ accountId));
    }
}
