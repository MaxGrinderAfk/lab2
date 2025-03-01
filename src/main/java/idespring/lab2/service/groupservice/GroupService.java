package idespring.lab2.service.groupservice;

import idespring.lab2.model.Group;
import java.util.List;

public interface GroupService {
    List<Group> readGroups(String namePattern, String sort);

    Group findById(Long id);

    Group findByName(String name);

    Group findByIdWithStudents(Long id);

    Group addGroup(Group group);

    void deleteGroup(Long id);

    void deleteGroupByName(String name);

    boolean existsByName(String name);
}