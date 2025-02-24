package idespring.lab2.controller.markcontroller;

import idespring.lab2.model.Mark;
import idespring.lab2.service.markservice.MarkService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
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

    @PostMapping("/add")
    public ResponseEntity<Mark> createMark(@Valid @RequestBody Mark mark) {
        return new ResponseEntity<>(markService.addMark(mark), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Mark>> getMarks(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long subjectId) {
        List<Mark> marks = markService.readMarks(studentId, subjectId);
        return !marks.isEmpty()
                ? new ResponseEntity<>(marks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{markId}")
    public ResponseEntity<Void> deleteMark(@Positive @NotNull @PathVariable Long markId) {
        markService.deleteMark(markId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
