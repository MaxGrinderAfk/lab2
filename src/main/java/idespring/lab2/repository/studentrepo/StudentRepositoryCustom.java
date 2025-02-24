package idespring.lab2.repository.studentrepo;

import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import java.util.List;

public interface StudentRepositoryCustom {
    List<Student> findByAgeAndSortByName(long age, String sort);

    List<Student> sortByName(String sort);
}
