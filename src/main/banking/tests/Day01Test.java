package banking.tests;

import banking.BankAccount;

import java.util.Scanner;

public class Day01Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Bank Account Simulator ===");
        System.out.print("Enter account ID: ");
        String id = sc.nextLine();
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter initial balance: ");
        double initialBalance = sc.nextDouble();

        BankAccount account = new BankAccount(id, name, initialBalance);

        boolean running = true;
        while(running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Print Account Dettails");
            System.out.println("5. Exit");
            System.out.println("Choose: ");

            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    System.out.print("Amount to deposit: ");
                    account.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Amount to withdraw: ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 3:
                    System.out.printf("Current balance: %.2f%n", account.getBalance());
                    break;
                case 4:
                    System.out.println(account.toString());
                    break;
                case 5:
                    running = false;
                    System.out.println("GoodBye!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
    }
}
