package org.tuyetdang.Service;

import org.springframework.stereotype.Component;
import org.tuyetdang.Entity.Invoice;
import org.tuyetdang.Entity.InvoiceItem;

@Component
public class PdfGeneratorUtil {
    public void generatePdf(Invoice invoice) {
        StringBuilder listProducts = new StringBuilder();

        for (InvoiceItem item : invoice.getInvoiceItems()) {
            listProducts.append("- ")
                    .append(item.getProduct().getProductName())
                    .append(" : ")
                    .append(item.getProduct().getProductPrice())
                    .append(" vnd ")
                    .append("\n");
        }

        String pdfContent = "PDF content for invoice: " + invoice.getInvoiceId()
                + "\nCustomer: " + invoice.getCustomer().getCustomerName()
                + "\nDate: " + invoice.getDate()
                + "\n\nProducts:\n" + listProducts
                + "\nTotal Amount: " + invoice.getTotalAmount();

        System.out.println(pdfContent);
    }
}
