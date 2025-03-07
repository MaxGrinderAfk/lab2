package idespring.lab2.service.subjectservice;

import idespring.lab2.model.Subject;
import java.util.List;

public interface SubjectService {
    List<Subject> readSubjects(String namePattern, String sort);

    Subject findById(Long id);

    Subject findByName(String name);

    Subject addSubject(Subject subject);

    void deleteSubject(Long id);

    void deleteSubjectByName(String name);

    boolean existsByName(String name);
}