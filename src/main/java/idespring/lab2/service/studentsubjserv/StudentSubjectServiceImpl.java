package idespring.lab2.service.studentsubjserv;

import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import idespring.lab2.repository.studentrepo.StudentRepository;
import idespring.lab2.repository.subjectrepo.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentSubjectServiceImpl(StudentRepository studentRepository,
                                     SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional
    public void addSubjectToStudent(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));


        studentRepository.addSubject(student.getId(), subject.getId());

        student.getSubjects().add(subject);
        if (subject.getStudents() == null) {
            subject.setStudents(new ArrayList<>());

            subject.getStudents().add(student);
        }
    }

    @Override
    @Transactional
    public void removeSubjectFromStudent(Long studentId, Long subjectId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        studentRepository.removeSubject(student.getId(), subject.getId());

        student.getSubjects().remove(subject);
        if (subject.getStudents() != null) {
            subject.getStudents().remove(student);
        }
    }

    @Override
    public List<Subject> getSubjectsByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        return new ArrayList<>(student.getSubjects());
    }

    @Override
    public List<Student> getStudentsBySubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        return new ArrayList<>(subject.getStudents());
    }
}