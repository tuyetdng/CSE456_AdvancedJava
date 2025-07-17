package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.tuyetdang.Entity.School;
import org.tuyetdang.infra.JpaUtil;

import java.util.List;

public class SchoolRepository {
    public void create(School school) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(school);
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

    public void update(School school) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(school);
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

    public School findById(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.find(School.class, id);
    }

    public List<School> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT s FROM School s", School.class).getResultList();
    }

    public School findByName(String name) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT s FROM School s WHERE s.schoolName = :name", School.class)
                .setParameter("name", name)
                .getSingleResult();
    }
    public void delete(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            School school = em.find(School.class, id);
            if (school != null) {
                em.remove(school);
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
