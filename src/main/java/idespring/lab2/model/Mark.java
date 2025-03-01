package idespring.lab2.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(schema = "studentmanagement", name = "marks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mark implements Imark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentid")
    @JsonIgnore
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectid")
    @JsonIgnore  // Заменяем аннотации, создающие цикл
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

    public Mark(int value, Istudent student, Isubject subject) {
        this.value = value;

        if (student instanceof Student) {
            this.student = (Student) student;
        } else if (student != null) {
            this.student = new Student();
            this.student.setId(student.getId());
            this.student.setName(student.getName());
            // Устанавливаем другие необходимые свойства
        }

        if (subject instanceof Subject) {
            this.subject = (Subject) subject;
        } else if (subject != null) {
            this.subject = new Subject();
            this.subject.setId(subject.getId());
            this.subject.setName(subject.getName());
        }
    }

    public Mark(int value) {
        this.value = value;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // Используем интерфейс в сигнатуре метода
    public Istudent getStudent() {
        return student;
    }

    public void setStudent(Istudent student) {
        if (student instanceof Student) {
            this.student = (Student) student;
        } else if (student != null) {
            this.student = new Student();
            this.student.setId(student.getId());
            this.student.setName(student.getName());
            // Можно установить и другие свойства, если они доступны через интерфейс
        } else {
            this.student = null;
        }
    }

    public Isubject getSubject() {
        return subject;
    }

    public void setSubject(Isubject subject) {
        if (subject instanceof Subject) {
            this.subject = (Subject) subject;
        } else if (subject != null) {
            this.subject = new Subject();
            this.subject.setId(subject.getId());
            this.subject.setName(subject.getName());
        } else {
            this.subject = null;
        }
    }

    @Override
    @JsonProperty
    public Long getStudentId() {
        return student != null ? student.getId() : null;
    }

    @Override
    @JsonProperty
    public String getStudentName() {
        return student != null ? student.getName() : null;
    }

    @Override
    @JsonProperty
    public Long getSubjectId() {
        return subject != null ? subject.getId() : null;
    }

    @Override
    @JsonProperty
    public String getSubjectName() {
        return subject != null ? subject.getName() : null;
    }

    public Subject.MarkInfo toMarkInfo() {
        return new Subject.MarkInfo(this.id, this.value, getStudentId(), getStudentName());
    }
}