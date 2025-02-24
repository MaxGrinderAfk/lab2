package idespring.lab2.service.groupservice;

import idespring.lab2.model.Group;
import java.util.List;

public interface GroupService {
    List<Group> readGroups(String namePattern, String sort);

    Group addGroup(Group group);

    void deleteGroup(Long id);

    boolean existsByName(String name);
}