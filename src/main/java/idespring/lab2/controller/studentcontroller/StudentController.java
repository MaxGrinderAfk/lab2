package idespring.lab2.controller.studentcontroller;

import idespring.lab2.model.Student;
import idespring.lab2.service.studservice.StudentServ;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentServ studentService;

    @Autowired
    public StudentController(StudentServ studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/addStud")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/get/{studentId}")
    public ResponseEntity<List<Student>>
        getStudentById(@Positive @NotNull @PathVariable Long studentId) {
        return !(studentService.readStudents(null, null, studentId).isEmpty())
                ? new ResponseEntity<>(studentService
                .readStudents(null, null, studentId), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Student>> getStudents(
            /*@Min(12) @Max(100)*/ @RequestParam(required = false) Integer age,
            /*@NotEmpty*/ @RequestParam(required = false) String sort) {
        return !(studentService.readStudents(age, sort, null).isEmpty())
                ? new ResponseEntity<>(studentService
                .readStudents(age, sort, null), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<List<Student>> updateStudent(@Positive @NotNull @PathVariable
                                                           Long studentId,
                                                       @RequestParam(required = false,
                                                               defaultValue = "unknown")
                                                       String name,
                                                       @RequestParam(required = false,
                                                               defaultValue = "15") int age) {
        studentService.updateStudent(name, age, studentId);
        return new ResponseEntity<>(studentService
                .readStudents(null, null, studentId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<List<Student>> deleteStudent(@Positive @NotNull @PathVariable
                                                           Long studentId) {
        studentService.deleteStudent(studentId);
        return !(studentService.readStudents(null, null, null).isEmpty())
                ? new ResponseEntity<>(studentService
                .readStudents(null, null, null), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
