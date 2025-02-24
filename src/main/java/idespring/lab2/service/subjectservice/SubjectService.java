package idespring.lab2.service.subjectservice;

import idespring.lab2.model.Subject;
import java.util.List;

public interface SubjectService {
    List<Subject> readSubjects(String namePattern, String sort);

    Subject addSubject(Subject subject);

    void deleteSubject(Long id);

    boolean existsByName(String name);
}
