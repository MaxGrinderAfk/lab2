package idespring.lab2.controller.subjectcontroller;

import idespring.lab2.model.Subject;
import idespring.lab2.service.subjectservice.SubjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/add")
    public ResponseEntity<Subject> createSubject(@Valid @RequestBody Subject subject) {
        if (subjectService.existsByName(subject.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(subjectService.addSubject(subject), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Subject>> getSubjects(
            @RequestParam(required = false) String namePattern,
            @RequestParam(required = false) String sort) {
        List<Subject> subjects = subjectService.readSubjects(namePattern, sort);
        return !subjects.isEmpty()
                ? new ResponseEntity<>(subjects, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@Positive @NotNull @PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
