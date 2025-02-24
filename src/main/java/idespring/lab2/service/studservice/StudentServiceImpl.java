package idespring.lab2.service.studservice;

import idespring.lab2.model.Student;
import idespring.lab2.repository.studentrepo.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentServ {
    private final StudentRepository studDao;

    @Autowired
    public StudentServiceImpl(StudentRepository studDao) {
        this.studDao = studDao;
    }

    @Override
    public List<Student> readStudents(Integer age, String sort, Long id) {
        if (id != null) {
            List<Student> students = new ArrayList<>();
            students.add(studDao.findById(id).orElse(null));

            return students;
        }

        List<Student> students;

        if (age != null && sort != null) {
            students = studDao.findByAgeAndSortByName(age, sort);
        } else if (age != null) {
            students = studDao.findByAge(age);
        } else if (sort != null) {
            students = studDao.sortByName(sort);
        } else {
            students = studDao.findAll();
        }
        return students;
    }

    @Override
    public Student addStudent(Student student) {
        return studDao.save(student);
    }

    @Override
    public void updateStudent(String name, int age, long id) {
        studDao.update(name, age, id);
    }

    @Override
    public void deleteStudent(long id) {
        studDao.delete(id);
    }
}
