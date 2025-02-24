package idespring.lab2.service.studservice;

import idespring.lab2.model.Student;
import java.util.List;

public interface StudentServ {
    List<Student> readStudents(Integer age, String sort, Long id);

    Student addStudent(Student student);

    void updateStudent(String name, int age, long id);

    void deleteStudent(long id);
}
