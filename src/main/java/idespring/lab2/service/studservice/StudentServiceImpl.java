package idespring.lab2.service.studservice;

import idespring.lab2.model.Student;
import idespring.lab2.repository.studentrepo.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentServ {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> readStudents(Integer age, String sort, Long id) {
        if (id != null) {
            return Collections.singletonList(
                    studentRepository.findById(id)
                            .orElseThrow(() -> new
                                    EntityNotFoundException("Student not found with id: " + id))
            );
        }

        if (age != null && sort != null) {
            return studentRepository.findByAgeAndSortByName(age, sort);
        } else if (age != null) {
            return studentRepository.findByAge(age).stream().toList();
        } else if (sort != null) {
            return studentRepository.sortByName(sort);
        } else {
            return studentRepository.findAll();
        }
    }

    @Override
    public List<Student> findByGroupId(Long groupId) {
        return studentRepository.findByGroupId(groupId).stream().toList();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student findByIdWithSubjects(Long id) {
        return studentRepository.findByIdWithSubjects(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student findByIdWithMarks(Long id) {
        return studentRepository.findByIdWithMarks(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student findByIdWithSubjectsAndMarks(Long id) {
        return studentRepository.findByIdWithSubjectsAndMarks(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void updateStudent(String name, int age, long id) {
        studentRepository.update(name, age, id);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.delete(id);
    }
}