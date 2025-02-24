package idespring.lab2.service.markservice;

import idespring.lab2.model.Mark;
import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import idespring.lab2.repository.markrepo.MarkRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public List<Mark> readMarks(Long studentId, Long subjectId) {
        Student tmpStudent = new Student();
        tmpStudent.setId(studentId);
        Subject tmpSubject = new Subject();
        tmpSubject.setId(subjectId);

        if (studentId != null && subjectId != null) {
            return markRepository.findByStudentAndSubject(
                    tmpStudent,
                    tmpSubject
            );
        } else if (studentId != null) {
            return markRepository.findByStudent(tmpStudent);
        } else if (subjectId != null) {
            return markRepository.findBySubject(tmpSubject);
        }
        return markRepository.findAll();
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