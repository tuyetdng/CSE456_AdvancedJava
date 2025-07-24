package org.tuyetdang.Service;

public interface PaymentStrategy {
    public void makePayment(double amount, Long senderId, Long receiverId, String cardNumber, String phoneNumber, String bankAccountNumber );
}
