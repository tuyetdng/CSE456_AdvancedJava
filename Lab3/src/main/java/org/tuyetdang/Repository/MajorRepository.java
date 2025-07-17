package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.tuyetdang.Entity.Major;
import org.tuyetdang.infra.JpaUtil;

import java.util.List;

public class MajorRepository {
    public void create(Major major) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(major);
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

    public void update(Major major) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(major);
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

    public Major findById(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.find(Major.class, id);
    }

    public List<Major> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT m FROM Major m", Major.class).getResultList();
    }

    public List<Major> findByName(String name) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT m FROM Major m WHERE m.majorName LIKE :name", Major.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public void delete(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Major major = em.find(Major.class, id);
            if (major != null) {
                em.remove(major);
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
