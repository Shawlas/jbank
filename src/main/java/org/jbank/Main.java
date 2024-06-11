package org.jbank;
import org.jbank.utils.Customer;
import org.jbank.utils.Deposit;
import org.jbank.utils.Withdraw;
import org.jetbrains.annotations.NotNull;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Customer> customers = new ArrayList<Customer>();
    private int choice;

    public static void main(String[] args) throws Exception {
        sc.useLocale(Locale.US);

        Main main = new Main();
        main.menu();

    }

    public void menu() throws Exception {
        System.out.println("\n (1) Insert Account  |  (2)  Deposit  |  (3)  Withdraw  |  (4) Exit");
        int choice = sc.nextInt();

        while (choice < 1 || choice > 4) {
            System.out.println("\n ==== Invalid Command ====");
            System.out.println("\n (1) Insert Account  |  (2)  Deposit  |  (3)  Withdraw  |  (4) Exit");
            choice = sc.nextInt();
        }

        if (choice == 1) {
            insertAccount();
            menu();
        } else if (choice == 2) {

            System.out.print("\nEnter the ID Customer: ");
            @NotNull String id = sc.next();
            for (Customer i: customers) {
                if (!Objects.equals(i.getId(), id)) {
                    System.out.print("\nID not found.");
                    return;
                } else {
                    System.out.println("\n Name: " + i.getName() + " " + i.getSurname());
                    System.out.println(" ID: " + i.getId());
                    System.out.println(" Email: " + i.getEmail());
                    System.out.printf(" Balance: $%.2f", i.getBalance());
                    System.out.print("\n Enter the deposit value: ");
                    double value = sc.nextDouble();
                    Deposit deposit = new Deposit(i);
                    deposit.getCustomer().setBalance(value, deposit.getIsAdd());
                    System.out.println("\n Name: " + deposit.getCustomer().getName()+ " " + deposit.getCustomer().getSurname());
                    System.out.println(" ID: " + deposit.getCustomer().getId());
                    System.out.println(" Email: " + deposit.getCustomer().getEmail());
                    System.out.printf(" Balance: $%.2f", deposit.getCustomer().getBalance());
                }
            }
            menu();
        } else if (choice == 3) {
            System.out.print("\nEnter the ID Customer: ");
            @NotNull String id = sc.next();
            for (Customer i: customers) {
                if (!Objects.equals(i.getId(), id)) {
                    System.out.print("\nID not found.");
                    return;
                } else {
                    System.out.println("\n Name: " + i.getName() + " " + i.getSurname());
                    System.out.println(" ID: " + i.getId());
                    System.out.println(" Email: " + i.getEmail());
                    System.out.printf(" Balance: $%.2f", i.getBalance());
                    System.out.print("\n Enter the withdraw value: ");
                    double value = sc.nextDouble();
                    Withdraw withdraw = new Withdraw(i);
                    withdraw.getCustomer().setBalance(value, withdraw.getIsAdd());

                    System.out.println("\n Name: " + withdraw.getCustomer().getName() + " " + withdraw.getCustomer().getSurname());
                    System.out.println(" ID: " + withdraw.getCustomer().getId());
                    System.out.println(" Email: " + withdraw.getCustomer().getEmail());
                    System.out.printf(" Balance: $%.2f", withdraw.getCustomer().getBalance());
                }
            }
            menu();
        } else {
            System.exit(0);
        }
    }

    public void insertAccount() throws Exception {
        @NotNull String name;
        @NotNull String surname;
        @NotNull String id;
        @NotNull String email;
        double balance;

        try {
            System.out.print("\n Enter the name: ");
            name = sc.next();
            System.out.print("\n Enter the surname: ");
            surname = sc.next();
            System.out.print("\n Enter the ID: ");
            id = sc.next();
            System.out.print("\n Enter the email: ");
            email = sc.next();
            System.out.print("\n Initial Balance: ");
            balance = sc.nextDouble();
            Customer user = new Customer(name,surname,id,email,balance);
            customers.add(user);


        } catch (Exception e) {
            throw new Exception("Something is wrong. Please, try later.");
        }

    }

    public void scannerClose() {
        sc.close();
    }
}




