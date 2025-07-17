package org.tuyetdang.Repository;

import jakarta.persistence.EntityManager;
import org.tuyetdang.Entity.Student;
import org.tuyetdang.infra.JpaUtil;

import java.util.List;

public class StudentRepository {

    public void create(Student student) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
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

    public void update(Student student) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(student);
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

    public Student findById(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.find(Student.class, id);
    }

//    public void updateStudentMajor()

    public List<Student> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    public List<Student> findByName(String name) {
        EntityManager em = JpaUtil.getEntityManager();
        return em.createQuery("SELECT s FROM Student s WHERE s.fullName LIKE :name", Student.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public void delete(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
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
