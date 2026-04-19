package banking;

import banking.CheckingAccount;
import banking.SavingsAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<BankAccount> accounts = new ArrayList<>(); // holds ANY account type

        boolean running = true;
        while (running) {
            System.out.println("\n=== " + BankAccount.getBankName() + " ===");
            System.out.println("1. New Savings Account");
            System.out.println("2. New Checking Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. List all accounts");
            System.out.println("6. Apply interest (savings only)");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("ID: "); String id = sc.nextLine();
                    System.out.print("Name: "); String nm = sc.nextLine();
                    System.out.print("Opening deposit: "); double dep = sc.nextDouble(); sc.nextLine();
                    try { accounts.add(new SavingsAccount(id, nm, dep)); System.out.println("Savings account created."); }
                    catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                    break;
                case 2:
                    System.out.print("ID: "); String id2 = sc.nextLine();
                    System.out.print("Name: "); String nm2 = sc.nextLine();
                    System.out.print("Opening deposit: "); double dep2 = sc.nextDouble(); sc.nextLine();
                    try { accounts.add(new CheckingAccount(id2, nm2, dep2)); System.out.println("Checking account created."); }
                    catch (IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
                    break;
                case 3:
                    System.out.print("Account ID: "); String tid = sc.nextLine();
                    BankAccount t = find(accounts, tid);
                    if (t == null) { System.out.println("Not found."); break; }
                    System.out.print("Amount: "); t.deposit(sc.nextDouble()); sc.nextLine();
                    break;
                case 4:
                    System.out.print("Account ID: "); String tid2 = sc.nextLine();
                    BankAccount t2 = find(accounts, tid2);
                    if (t2 == null) { System.out.println("Not found."); break; }
                    System.out.print("Amount: "); t2.withdraw(sc.nextDouble()); sc.nextLine();
                    break;
                case 5:
                    accounts.forEach(System.out::println);
                    break;
                case 6:
                    accounts.stream()
                            .filter(a -> a instanceof SavingsAccount)
                            .map(a -> (SavingsAccount) a)
                            .forEach(SavingsAccount::applyMonthlyInterest);
                    break;
                case 7: running = false; break;
            }
        }
        sc.close();
    }

    private static BankAccount find(List<BankAccount> list, String id) {
        return list.stream().filter(a -> a.getAccountId().equals(id)).findFirst().orElse(null);
    }
}