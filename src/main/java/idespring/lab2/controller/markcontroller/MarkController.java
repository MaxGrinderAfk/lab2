package idespring.lab2.controller.markcontroller;

import idespring.lab2.model.Mark;
import idespring.lab2.service.markservice.MarkService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marks")
public class MarkController {
    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping
    public ResponseEntity<Mark> createMark(@Valid @RequestBody Mark mark) {
        return new ResponseEntity<>(markService.addMark(mark), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<Mark>> getMarks(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long subjectId) {
        Set<Mark> marks = new HashSet<>(markService.readMarks(studentId, subjectId));
        return !marks.isEmpty()
                ? new ResponseEntity<>(marks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/value/{value}")
    public ResponseEntity<Set<Mark>> getMarksByValue(@Positive @PathVariable int value) {
        Set<Mark> marks = new HashSet<>(markService.findByValue(value));
        return !marks.isEmpty()
                ? new ResponseEntity<>(marks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/average/student/{studentId}")
    public ResponseEntity<Double> getAverageMarkByStudent(
            @Positive @NotNull @PathVariable Long studentId) {
        Double average = markService.getAverageMarkByStudentId(studentId);
        return average != null
                ? new ResponseEntity<>(average, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/average/subject/{subjectId}")
    public ResponseEntity<Double> getAverageMarkBySubject(
            @Positive @NotNull @PathVariable Long subjectId) {
        Double average = markService.getAverageMarkBySubjectId(subjectId);
        return average != null
                ? new ResponseEntity<>(average, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{markId}")
    public ResponseEntity<Void> deleteMark(@Positive @NotNull @PathVariable Long markId) {
        try {
            markService.deleteMark(markId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}