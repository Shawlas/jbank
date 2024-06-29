package org.jbank.main;
import org.jbank.entities.*;
import org.jbank.utils.Deposit;
import org.jbank.utils.Withdraw;

public class Main {

    public static void main(String[] args) throws Exception {

        Customer customer = new Customer("asd123", "henrique", "sousa", "123239", "henrique@gmail");

        TransactionAccount account = new TransactionAccount(customer, "a-bc154");

        Movement deposit = new Deposit(1, 300, account);

        System.out.println(account.getBalance() + ": " + deposit.getDateTime());

        Movement withdraw = new Withdraw(1, 150, account);

        System.out.println(account.getBalance() + ": " + withdraw.getDateTime());

    }

}