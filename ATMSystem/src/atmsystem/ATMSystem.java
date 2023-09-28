/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmsystem;

/**
 *
 * @author anujp
 */


    import java.util.Scanner;

class Account {
    private String userId;
    private String userPin;
    private double balance;

    public Account(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
    }

    public boolean authenticate(String userId, String userPin) {
        return this.userId.equals(userId) && this.userPin.equals(userPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: Rs." + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: Rs." + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transferred: Rs." + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        Account account = new Account("Krishna", "123456", 1000.0); 
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM System!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        if (account.authenticate(userId, userPin)) {
            System.out.println("Authentication successful.");
            boolean quit = false;

            while (!quit) {
                System.out.println("\nSelect an option:");
                System.out.println("1. View Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Balance: $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        account.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientUserId = scanner.next();
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        Account recipient = new Account(recipientUserId, "", 0); 
                        account.transfer(recipient, transferAmount);
                        break;
                    case 5:
                        quit = true;
                        System.out.println("Thank you for using the ATM!");
                        break;
                    default:
                        System.out.println("Invalid option. Please select again.");
                }
            }
        } else {
            System.out.println("Authentication failed. Exiting.");
        }

        scanner.close();
    }
}
// In a real system, you'd fetch the recipient's account