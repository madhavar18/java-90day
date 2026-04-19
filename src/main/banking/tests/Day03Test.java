package banking.tests;

import banking.BankAccount;
import banking.SavingsAccount;
import banking.CheckingAccount;

public class Day03Test {
    public static void main(String[] args) {
        //Test 1 - IS-A: SavingsAccount IS-A BankAccount
        SavingsAccount savings = new SavingsAccount("SAV001", "Ravi", 10000.0);
        System.out.println("Is SavingsAccount a BankAccount? "
                + (savings instanceof BankAccount)); //true
        System.out.println(savings);

        //Test 2 - Inherited deposit() works on SavingsAccount
        savings.deposit(5000.0);

        //Test 3 - Overridden withdraw() enforces 3-withdrawal limit
        System.out.println("\n--- Testing withdrawal limit ---");
        savings.withdraw(1000.0);
        savings.withdraw(1000.0);
        savings.withdraw(1000.0);
        savings.withdraw(1000.0); //4th withdrawal - should be refused

        //Test 4 - Interest calculation
        System.out.println("\n--- Applying monthly interest ---");
        savings.applyMonthlyInterest();
        System.out.println(savings);

        //Test 5 - CheckingAccount with transaction fees
        System.out.println("\n--- CheckingAccount ---");
        CheckingAccount checking = new CheckingAccount("CHK001", "Abhi", 5000.0);
        checking.withdraw(500.0);
        checking.withdraw(500.0);
        checking.printAccountSummary();

        //Test 6 - polymorphism preview
        //A BankAccount variable can hold a SavingsAccount object
        System.out.println("\n--- Polymorphism preview ---");
        BankAccount acc = new SavingsAccount("SAV002", "Amit", 8000.0);
        acc.withdraw(1000.0);
        System.out.println(acc);
    }
}
