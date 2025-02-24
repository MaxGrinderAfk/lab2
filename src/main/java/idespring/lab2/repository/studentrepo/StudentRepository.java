package idespring.lab2.repository.studentrepo;

import idespring.lab2.model.Student;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {

    List<Student> findByAge(int age);

    Student findById(long id);

    @Query("UPDATE Student s SET s.name = :name, s.age = :age WHERE s.id = :id")
    @Modifying
    void update(@Param("name") String name, @Param("age") int age, @Param("id") long id);

    @Query("DELETE FROM Student s WHERE s.id = :id")
    @Transactional
    @Modifying
    void delete(@Param("id") long id);

    @Modifying
    @Query(value = "INSERT INTO studentmanagement.student_subject "
            + "(studentid, subjectid) VALUES (:studentId, :subjectId)", nativeQuery = true)
    void addSubject(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);

    @Modifying
    @Query(value = "DELETE FROM studentmanagement.student_subject "
            + "WHERE studentid = :studentId AND subjectid = :subjectId", nativeQuery = true)
    void removeSubject(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
}
