package org.jbank.main;
import org.jbank.entities.*;
import org.jbank.utils.Deposit;
import org.jbank.utils.Transference;
import org.jbank.utils.Withdraw;

public class Main {

    public static void main(String[] args) throws Exception {

        Customer customer = new Customer("1", "henrique", "sousa", "123239", "henrique@gmail");
        Customer customer1 = new Customer("2", "joao", "silva", "5323", "joao@gmail");

        TransactionAccount account = new TransactionAccount(customer, "a-1234");
        TransactionAccount account1 = new TransactionAccount(customer1, "a-bc154");

        System.out.println(customer.getAccount());
        System.out.println(customer1.getAccount());

        Deposit.deposit(account, 3500);
        Deposit.deposit(account1, 1500);

        System.out.println(customer.getAccount());
        System.out.println(customer1.getAccount());


        Transference.transference(account, 200, account1);

        System.out.println(customer.getAccount());
        System.out.println(customer1.getAccount());

        Withdraw.withdraw(account, 1000);
        Withdraw.withdraw(account1, 500);

        System.out.println(customer.getAccount());
        System.out.println(customer1.getAccount());

    }

}