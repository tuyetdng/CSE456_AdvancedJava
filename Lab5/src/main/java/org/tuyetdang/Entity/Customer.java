package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tuyetdang.Entity.Enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long customerId;
    String customerName;
    String customerEmail;
    String customerPhone;
    String customerAddress;
    Gender gender;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Invoice> invoices = new ArrayList<>();

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setCustomer(this);
    }

    public Customer(String customerName, String customerEmail, String customerPhone, String customerAddress, Gender gender) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.gender = gender;
    }
}
