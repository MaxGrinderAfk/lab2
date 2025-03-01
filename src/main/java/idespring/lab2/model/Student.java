package idespring.lab2.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "studentmanagement", name = "students")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student implements Istudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupid", nullable = true)
    @JsonIdentityReference(alwaysAsId = true)
    private Group group;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable (
            name = "student_subject",
            joinColumns = @JoinColumn(name = "studentid"),
            inverseJoinColumns = @JoinColumn(name = "subjectid")
    )
    private Set<Subject> subjects = new HashSet<>();

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Mark> marks = new HashSet<>();

    public Student() {}

    public Student(Long id) {
        this.id = id;
    }

    public Student(String name, int age, Group group, Set<Subject> subjects) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.subjects = subjects;
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
    public Set<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public Set<Mark> getMarks() {
        return marks;
    }

    @Override
    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public Igroup getGroup() {
        return (Igroup) group;
    }

    @Override
    public void setGroup(Igroup group) {
        this.group = (Group) group;
    }
}