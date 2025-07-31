package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.tuyetdang.Entity.Customer;
import org.tuyetdang.Infra.JpaUtil;

@Repository
public class CustomerRepository {

    public void createCustomer(Customer customer) {
        EntityManager em = JpaUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
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
