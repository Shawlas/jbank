package org.jbank.main;
import org.jbank.utils.Customer;
import org.jbank.utils.Deposit;
import org.jbank.utils.Movement;
import org.jbank.utils.Withdraw;
import org.jetbrains.annotations.NotNull;

import java.time.*;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer("test","testJR","1242512516","5555555","test@email");

        System.out.println(customer.getBalance());

        Movement deposit = new Deposit(1,customer, 2000);

        System.out.println(customer.getBalance());

        Movement withdraw = new Withdraw(3, customer, 1000);

        System.out.println(customer.getBalance());

        System.out.println(deposit.getDateTime());

        System.out.println(deposit.getId() + " " + withdraw.getId());

    }
}