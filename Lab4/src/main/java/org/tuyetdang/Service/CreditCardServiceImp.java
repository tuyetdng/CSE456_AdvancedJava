package org.tuyetdang.Service;

public class CreditCardServiceImp implements PaymentStrategy {
    @Override
    public void makePayment(double amount, Long senderId, Long receiverId, String cardNumber, String phoneNumber, String bankAccountNumber) {
        System.out.println("Processing credit card payment...");
        System.out.printf("Amount: %.2f, Sender ID: %s, Receiver ID: %s, Card Number: %s%n",
                amount, senderId, receiverId, cardNumber);
    }
}
