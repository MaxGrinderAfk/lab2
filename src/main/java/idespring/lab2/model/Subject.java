package idespring.lab2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "studentmanagement", name = "subjects")
public class Subject implements Isubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mark> marks = new HashSet<>();

    public Subject() {}

    public Subject(String name, Set<Mark> marks) {
        this.name = name;
        this.marks = marks;
    }

    public Subject(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public Set<StudentInfo> getStudentsInfo() {
        Set<StudentInfo> infos = new HashSet<>();
        for (Student student : students) {
            infos.add(new StudentInfo(student.getId(), student.getName(), student.getAge()));
        }
        return infos;
    }

    @Override
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public Set<Mark> getMarks() {
        return marks;
    }

    @Override
    public Set<MarkInfo> getMarksInfo() {
        Set<MarkInfo> infos = new HashSet<>();
        for (Mark mark : marks) {
            infos.add(new MarkInfo(mark.getId(),
                    mark.getValue(), mark.getStudentId(), mark.getStudentName()));
        }
        return infos;
    }

    @Override
    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public void addMark(Mark mark) {
        marks.add(mark);
        mark.setSubject(this);
    }

    @Override
    public void removeMark(Mark mark) {
        marks.remove(mark);
        mark.setSubject(null);
    }

    public record StudentInfo(Long id, String name, int age) {}

    public record MarkInfo(Long id, int value, Long studentId, String studentName) {}

}