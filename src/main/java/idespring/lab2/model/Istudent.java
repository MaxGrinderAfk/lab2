package idespring.lab2.model;

import java.util.Set;

public interface Istudent {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Set<Subject> getSubjects();

    void setSubjects(Set<Subject> subjects);

    Set<Mark> getMarks();

    void setMarks(Set<Mark> marks);

    int getAge();

    Igroup getGroup();

    void setGroup(Igroup group);
}
