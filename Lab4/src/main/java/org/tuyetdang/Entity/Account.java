package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;
    String username;
    String password;
    String fullName;
    String email;
    String cardNumber;
    String phoneNumber;
    String bankAccountNumber;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Transaction> sentTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Transaction> receivedTransactions = new ArrayList<>();

    public void addSendTransaction(Transaction transaction) {
        sentTransactions.add(transaction);
        transaction.setSender(this);
    }

    public void addReceiveTransaction(Transaction transaction) {
        receivedTransactions.add(transaction);
        transaction.setReceiver(this);
    }

    public Account(String username, String password, String fullName, String email, String cardNumber, String phoneNumber, String bankAccountNumber) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
        this.bankAccountNumber = bankAccountNumber;
    }
}
