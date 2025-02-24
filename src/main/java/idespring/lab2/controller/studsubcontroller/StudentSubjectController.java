package idespring.lab2.controller.studsubcontroller;

import idespring.lab2.model.Student;
import idespring.lab2.model.Subject;
import idespring.lab2.service.studentsubjserv.StudentSubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student-subjects")
public class StudentSubjectController {
    private final StudentSubjectService studentSubjectService;

    @Autowired
    public StudentSubjectController(StudentSubjectService studentSubjectService) {
        this.studentSubjectService = studentSubjectService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSubjectToStudent(
            @RequestParam Long studentId,
            @RequestParam Long subjectId) {
        studentSubjectService.addSubjectToStudent(studentId, subjectId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeSubjectFromStudent(
            @RequestParam Long studentId,
            @RequestParam Long subjectId) {
        studentSubjectService.removeSubjectFromStudent(studentId, subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsByStudent(@PathVariable Long studentId) {
        List<Subject> subjects = studentSubjectService.getSubjectsByStudent(studentId);
        return !subjects.isEmpty()
                ? new ResponseEntity<>(subjects, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/subject/{subjectId}/students")
    public ResponseEntity<List<Student>> getStudentsBySubject(@PathVariable Long subjectId) {
        List<Student> students = studentSubjectService.getStudentsBySubject(subjectId);
        return !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}