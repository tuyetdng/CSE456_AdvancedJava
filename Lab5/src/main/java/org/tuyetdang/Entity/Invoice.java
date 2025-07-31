package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long invoiceId;
    LocalDate date;
    double totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    @ToString.Exclude
    Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    List<InvoiceItem> invoiceItems = new ArrayList<>();

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItems.add(invoiceItem);
        invoiceItem.setInvoice(this);
    }

    public Invoice(LocalDate date, double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

}
