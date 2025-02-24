package idespring.lab2.repository.studentrepo;

import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.*;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Student> sortByName(String sort) {
        String orderBy = "ASC";
        if ("DESC".equalsIgnoreCase(sort)) {
            orderBy = "DESC";
        }

        String jpql = "SELECT s FROM Student s ORDER BY s.name" + orderBy;
        return em.createQuery(jpql, Student.class).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findByAgeAndSortByName(long age, String sort) {

        String orderBy = "ASC";
        if ("DESC".equalsIgnoreCase(sort)) {
            orderBy = "DESC";
        }

        String sql = "SELECT * FROM studentmanagement.students WHERE age = :age ORDER BY name "
                + orderBy;

        return (List<Student>) em.createNativeQuery(sql, Student.class)
                .setParameter("age", age)
                .getResultList();
    }
}