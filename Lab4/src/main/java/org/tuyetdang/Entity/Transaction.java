package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tuyetdang.Entity.Enum.PaymentMethod;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long transactionId;
    double amount;
    String time;
    PaymentMethod paymentMethod;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    @ToString.Exclude
    Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    @ToString.Exclude
    Account receiver;


    public Transaction(double amount, String time, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.time = Timestamp.from(Instant.now()).toString();
        this.paymentMethod = paymentMethod;
    }

}
