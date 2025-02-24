package idespring.lab2.repository.markrepo;

import idespring.lab2.model.Mark;
import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findByStudent(Student student);

    List<Mark> findBySubject(Subject subject);

    List<Mark> findByStudentAndSubject(Student student, Subject subject);

    List<Mark> findByValue(int value);

    void deleteByStudent(Student student);

    void deleteBySubject(Subject subject);
}
