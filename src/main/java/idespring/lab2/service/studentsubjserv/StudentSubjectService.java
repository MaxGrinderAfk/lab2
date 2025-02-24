package idespring.lab2.service.studentsubjserv;

import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import java.util.List;

public interface StudentSubjectService {
    void addSubjectToStudent(Long studentId, Long subjectId);

    void removeSubjectFromStudent(Long studentId, Long subjectId);

    List<Subject> getSubjectsByStudent(Long studentId);

    List<Student> getStudentsBySubject(Long subjectId);
}
