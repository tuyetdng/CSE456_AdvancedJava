package org.tuyetdang;

import org.tuyetdang.Entity.Account;
import org.tuyetdang.Entity.Transaction;
import org.tuyetdang.Repository.AccountRepository;
import org.tuyetdang.Repository.TransactionRepository;
import org.tuyetdang.Service.*;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService(new AccountRepository());
        Account account1 = new Account("alice123", "password123", "Alice Nguyen", "alice.nguyen@example.com", "4111111111111111", "0909123456", "123456789001");
        Account account2 = new Account("bobtran", "securePass!@#", "Bob Tran", "bob.tran@example.com", "5500000000000004", "0987654321", "987654321009");
        accountService.add(account1);
        accountService.add(account2);

        TransactionService transactionService = new TransactionService(new TransactionRepository(), new AccountRepository(), new CreditCardServiceImp());
        System.out.println("Processing transaction from Alice to Bob by Credit card:");
        transactionService.processTransaction(122000, account1.getAccountId(), account2.getAccountId(), account1.getCardNumber(), null, null);

        TransactionService transactionService2 = new TransactionService(new TransactionRepository(), new AccountRepository(), new EWalletServiceImp());
        System.out.println("Processing transaction from Bob to Alice by E-wallet:");
        transactionService2.processTransaction(50000, account1.getAccountId(), account2.getAccountId(), null, account1.getPhoneNumber(), null);

        TransactionService transactionService3 = new TransactionService(new TransactionRepository(), new AccountRepository(), new BankTransfeServiceIml());
        System.out.println("Processing transaction from Alice to Bob by Bank transfer:");
        transactionService3.processTransaction(100000, account1.getAccountId(), account2.getAccountId(), null, null, account1.getBankAccountNumber());

        System.out.println("All transactions processed successfully.");
        for (Transaction transaction : transactionService.getAllTransactions()) {
            System.out.println(transaction.toString());
        }

    }
}