package org.tuyetdang.Service;

public class EWalletServiceImp implements PaymentStrategy {
    @Override
    public void makePayment(double amount, Long senderId, Long receiverId, String cardNumber, String phoneNumber, String bankAccountNumber) {
        System.out.println("Processing e-wallet payment...");
        System.out.printf("Amount: %.2f, Sender ID: %s, Receiver ID: %s, Phone Number: %s%n",
                amount, senderId, receiverId, phoneNumber);
    }
}
