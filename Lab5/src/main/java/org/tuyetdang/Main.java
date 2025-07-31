package org.tuyetdang;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.tuyetdang.Configuration.ApplicationConfig;
import org.tuyetdang.Entity.Customer;
import org.tuyetdang.Entity.Enums.Gender;
import org.tuyetdang.Entity.Invoice;
import org.tuyetdang.Entity.InvoiceItem;
import org.tuyetdang.Entity.Product;
import org.tuyetdang.Service.CustomerService;
import org.tuyetdang.Service.InvoiceService;
import org.tuyetdang.Service.ProductService;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        CustomerService customerService = applicationContext.getBean(CustomerService.class);
        ProductService productService = applicationContext.getBean(ProductService.class);
        InvoiceService invoiceService = applicationContext.getBean(InvoiceService.class);

        Customer customer1 = new Customer("Nguyen A", "a@gmail.com", "0912345678", "Hà Nội", Gender.MALE),
                customer2 = new Customer("Tran B", "b@gmail.com", "0987654321", "TP.HCM", Gender.FEMALE),
                customer3 = new Customer("Le C", "c@gmail.com", "0909123456", "Đà Nẵng", Gender.OTHER);

        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
        customerService.addCustomer(customer3);

        Product product1 = new Product("Bút", "Bút bi xanh", 5000.0, 100),
                product2 = new Product("Vở", "Vở 100 trang", 12000.0, 200),
                product3 = new Product("Thước", "Thước nhựa 30cm", 8000.0, 150),
                product4 = new Product("Tẩy", "Tẩy nhỏ", 3000.0, 250),
                product5 = new Product("Compa", "Compa học sinh", 20000.0, 80);

        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);
        productService.addProduct(product4);
        productService.addProduct(product5);

        InvoiceItem item1 = new InvoiceItem(product1.getProductPrice());
        item1.setProduct(product1);
        InvoiceItem item2 = new InvoiceItem(product2.getProductPrice());
        item2.setProduct(product2);
        InvoiceItem item3 = new InvoiceItem(product3.getProductPrice());
        item3.setProduct(product3);

        double totalAmount = 0;
        for (InvoiceItem item : new InvoiceItem[]{item1, item2, item3}) {
            totalAmount += item.getPrice();
        }

        Invoice invoice = new Invoice(LocalDate.now(), totalAmount);
        invoice.setCustomer(customer1);
        invoice.addInvoiceItem(item1);
        invoice.addInvoiceItem(item2);
        invoice.addInvoiceItem(item3);

        invoiceService.createInvoice(invoice);

        invoiceService.printInvoice(invoice);
    }
}