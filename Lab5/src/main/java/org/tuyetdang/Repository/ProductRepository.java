package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.tuyetdang.Entity.Product;
import org.tuyetdang.Infra.JpaUtil;

@Repository
public class ProductRepository {

    public void saveProduct(Product product) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
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
}
