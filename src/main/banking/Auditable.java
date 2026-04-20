package banking;

public interface Auditable {
    void logTransaction(String action, double amount);

    // Default method - interfaces CAN have implementations since java 8
    // WHY: lets you add new methods to an interface without breakingg existing implementations
    default void logDeposit(double amount) {
        logTransaction("DEPOSIT", amount);
    }

    default void logWithdrawal(double amount) {
        logTransaction("WITHDRAWAL", amount);
    }
}
