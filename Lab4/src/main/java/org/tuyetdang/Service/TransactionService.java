package org.tuyetdang.Service;

import org.tuyetdang.Entity.Account;
import org.tuyetdang.Entity.Enum.PaymentMethod;
import org.tuyetdang.Entity.Transaction;
import org.tuyetdang.Repository.AccountRepository;
import org.tuyetdang.Repository.TransactionRepository;

import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final PaymentStrategy paymentStrategy;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, PaymentStrategy paymentStrategy) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.paymentStrategy = paymentStrategy;
    }

    public void processTransaction(
            double amount,
            Long senderId,
            Long receiverId,
            String cardNumber,
            String phoneNumber,
            String bankAccountNumber
    ) {
        paymentStrategy.transfer(amount, senderId, receiverId, cardNumber, phoneNumber, bankAccountNumber);

        Account sender = accountRepository.findById(senderId);
        Account receiver = accountRepository.findById(receiverId);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTime(java.time.LocalDateTime.now().toString());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);

        if (cardNumber != null && !cardNumber.isEmpty()) {
            transaction.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
            transaction.setPaymentMethod(PaymentMethod.E_WALLET);
        } else if (bankAccountNumber != null && !bankAccountNumber.isEmpty()) {
            transaction.setPaymentMethod(PaymentMethod.BANK_TRANSFER);
        }
        transactionRepository.create(transaction);
    }


    public void add(Transaction transaction) {
        transactionRepository.create(transaction);
    }

    public void update(Transaction transaction) {
        transactionRepository.update(transaction);
    }

    public Transaction getById(long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void delete(long id) {
        transactionRepository.delete(id);
    }
}
