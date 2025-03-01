package idespring.lab2.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(schema = "studentmanagement", name = "marks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Только для десериализации
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectid")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Только для десериализации
    private Subject subject;

    public Mark() {}

    public void setStudentId(Long studentId) {
        if (studentId != null) {
            this.student = new Student();
            this.student.setId(studentId);
        }
    }

    public void setSubjectId(Long subjectId) {
        if (subjectId != null) {
            this.subject = new Subject();
            this.subject.setId(subjectId);
        }
    }

    public Mark(int value, Student student, Subject subject) {
        this.value = value;
        this.student = student;
        this.subject = subject;
    }

    public Mark(int value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Long getStudentId() {
        return student != null ? student.getId() : null;
    }

    public String getStudentName() {
        return student != null ? student.getName() : null;
    }

    public Long getSubjectId() {
        return subject != null ? subject.getId() : null;
    }

    public String getSubjectName() {
        return subject != null ? subject.getName() : null;
    }
}