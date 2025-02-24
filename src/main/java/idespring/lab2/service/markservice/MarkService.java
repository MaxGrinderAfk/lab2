package idespring.lab2.service.markservice;

import idespring.lab2.model.Mark;
import java.util.List;

public interface MarkService {
    List<Mark> readMarks(Long studentId, Long subjectId);

    Mark addMark(Mark mark);

    void deleteMark(Long id);
}
