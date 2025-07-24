package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.tuyetdang.Entity.Account;
import org.tuyetdang.Infra.JpaUtil;

import java.util.List;

public class AccountRepository {
    public void create(Account account) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(account);
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

    public void update(Account account) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(account);
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

    public Account findById(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.find(Account.class, id);
    }

    public List<Account> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT s FROM Account s", Account.class).getResultList();
    }

    public void delete(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Account Account = em.find(Account.class, id);
            if (Account != null) {
                em.remove(Account);
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
