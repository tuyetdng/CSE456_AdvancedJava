package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.tuyetdang.Entity.Invoice;
import org.tuyetdang.Entity.InvoiceItem;
import org.tuyetdang.Infra.JpaUtil;

@Repository
public class InvoiceRepository {

    public void saveInvoice(Invoice invoice) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(invoice);

            for (InvoiceItem item : invoice.getInvoiceItems()) {
                item.setInvoice(invoice);
                em.persist(item);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Invoice getInvoice(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Invoice.class, id);
        } finally {
            em.close();
        }
    }
}
