package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.tuyetdang.Entity.Transaction;
import org.tuyetdang.Infra.JpaUtil;

import java.util.List;

public class TransactionRepository {
    public void create(Transaction transaction) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(transaction);
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

    public void update(Transaction transaction) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(transaction);
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

    public Transaction findById(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.find(Transaction.class, id);
    }

    public List<Transaction> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT s FROM Transaction s", Transaction.class).getResultList();
    }

    public void delete(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Transaction transaction = em.find(Transaction.class, id);
            if (transaction != null) {
                em.remove(transaction);
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
}
