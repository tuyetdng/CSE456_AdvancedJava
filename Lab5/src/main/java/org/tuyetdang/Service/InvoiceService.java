package org.tuyetdang.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.tuyetdang.Entity.Invoice;
import org.tuyetdang.Repository.InvoiceRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceService {
     InvoiceRepository invoiceRepository;
     PdfGeneratorUtil pdfGeneratorUtil;

     public void createInvoice(Invoice invoice) {
         invoiceRepository.saveInvoice(invoice);
     }

     public void printInvoice(Invoice invoice) {
         pdfGeneratorUtil.generatePdf(invoice);
     }
}
