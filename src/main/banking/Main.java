package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankRepository repo = new BankRepository();

        boolean running = true;
        while (running) {
            System.out.println("\n=== " + BankAccount.getBankName() + " ===");
            System.out.println("1. New Savings Account");
            System.out.println("2. New Checking Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Find account by ID");
            System.out.println("6. List all accounts");
            System.out.println("7. List by type");
            System.out.println("8. Bank summary");
            System.out.println("9. Remove account");
            System.out.println("10. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1: case 2:
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Name: "); String nm = sc.nextLine();
                    System.out.print("Opening deposit: "); double dep = sc.nextDouble(); sc.nextLine();
                    try {
                        BankAccount acc = (ch == 1)
                                ? new SavingsAccount(id, nm, dep)
                                : new CheckingAccount(id, nm, dep);
                        repo.addAccount(acc);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Account ID: "); String did = sc.nextLine();
                    System.out.print("Amount: "); double da = sc.nextDouble(); sc.nextLine();
                    try { repo.deposit(did, da); }
                    catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
                    break;
                case 4:
                    System.out.print("Account ID: "); String wid = sc.nextLine();
                    System.out.print("Amount: "); double wa = sc.nextDouble(); sc.nextLine();
                    try { repo.withdraw(wid, wa); }
                    catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
                    break;
                case 5:
                    System.out.print("Account ID: "); String sid = sc.nextLine();
                    repo.findById(sid).ifPresentOrElse(
                            a -> System.out.println(a),
                            () -> System.out.println("Account not found.")
                    );
                    break;
                case 6:
                    repo.getAllAccounts().forEach(System.out::println);
                    break;
                case 7:
                    System.out.print("Type (SAVINGS/CHECKING): "); String type = sc.nextLine();
                    repo.findByType(type).forEach(System.out::println);
                    break;
                case 8:
                    repo.printSummary();
                    break;
                case 9:
                    System.out.print("Account ID to remove: "); String rid = sc.nextLine();
                    if (!repo.removeAccount(rid)) System.out.println("Account not found.");
                    break;
                case 10:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}