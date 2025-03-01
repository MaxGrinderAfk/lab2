package idespring.lab2.model;

import java.util.HashSet;
import java.util.Set;

public interface Isubject {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Set<Student> getStudents();

    Set<Subject.StudentInfo> getStudentsInfo();

    void setStudents(Set<Student> students);

    Set<Mark> getMarks();

    Set<Subject.MarkInfo> getMarksInfo();

    void setMarks(Set<Mark> marks);

    void addMark(Mark mark);

    void removeMark(Mark mark);
}
