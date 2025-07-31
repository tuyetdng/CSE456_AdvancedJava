package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long invoiceItemId;
    Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoiceId")
    @ToString.Exclude
    Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    @ToString.Exclude
    Product product;

    public InvoiceItem(Double price) {
        this.price = price;
    }
}
