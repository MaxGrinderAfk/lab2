package idespring.lab2.service.markservice;

import idespring.lab2.model.Mark;
import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import idespring.lab2.repository.markrepo.MarkRepository;
import idespring.lab2.repository.studentrepo.StudentRepository;
import idespring.lab2.repository.subjectrepo.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository,
                           StudentRepository studentRepository,
                           SubjectRepository subjectRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Mark> readMarks(Long studentId, Long subjectId) {
        if (studentId != null && subjectId != null) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new
                            EntityNotFoundException("Student not found with id: " + studentId));
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new
                            EntityNotFoundException("Subject not found with id: " + subjectId));

            return markRepository.findByStudentAndSubject(student, subject);
        } else if (studentId != null) {
            return markRepository.findByStudentId(studentId);
        } else if (subjectId != null) {
            return markRepository.findBySubjectId(subjectId);
        }
        return markRepository.findAll();
    }

    @Override
    public List<Mark> findByValue(int value) {
        return markRepository.findByValue(value);
    }

    @Override
    public Double getAverageMarkByStudentId(Long studentId) {
        return markRepository.getAverageMarkByStudentId(studentId);
    }

    @Override
    public Double getAverageMarkBySubjectId(Long subjectId) {
        return markRepository.getAverageMarkBySubjectId(subjectId);
    }

    @Override
    public Mark addMark(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public void deleteMark(Long id) {
        markRepository.deleteById(id);
    }
}