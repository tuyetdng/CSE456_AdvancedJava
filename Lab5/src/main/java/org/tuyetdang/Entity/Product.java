package org.tuyetdang.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long productId;
    String productName;
    String productDescription;
    Double productPrice;
    Integer productQuantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<InvoiceItem> invoiceItems = new ArrayList<>();

public void addInvoiceItem(InvoiceItem invoiceItem) {
    invoiceItems.add(invoiceItem);
        invoiceItem.setProduct(this);
}

    public Product(String productName, String productDescription, Double productPrice, Integer productQuantity) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
}
