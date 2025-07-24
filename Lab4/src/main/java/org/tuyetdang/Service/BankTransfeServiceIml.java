package org.tuyetdang.Service;

public class BankTransfeServiceIml implements PaymentStrategy {
    @Override
    public void makePayment(double amount, Long senderId, Long receiverId, String cardNumber, String phoneNumber, String bankAccountNumber) {
        System.out.println("Processing bank transfer payment...");
        System.out.printf("Amount: %.2f, Sender ID: %s, Receiver ID: %s, Bank Account Number: %s%n",
                amount, senderId, receiverId, bankAccountNumber);
    }
}
