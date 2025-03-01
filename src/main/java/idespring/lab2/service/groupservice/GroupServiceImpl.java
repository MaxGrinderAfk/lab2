package idespring.lab2.service.groupservice;

import idespring.lab2.model.Group;
import idespring.lab2.repository.grouprepo.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> readGroups(String namePattern, String sort) {
        if (namePattern != null) {
            return groupRepository.findByNameContaining(namePattern);
        }
        if (sort != null && sort.equalsIgnoreCase("asc")) {
            return groupRepository.findAllByOrderByNameAsc();
        }
        return groupRepository.findAll();
    }

    @Override
    public Group findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + id));
    }

    @Override
    public Group findByName(String name) {
        return groupRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with name: "
                        + name));
    }

    @Override
    public Group findByIdWithStudents(Long id) {
        return groupRepository.findByIdWithStudents(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + id));
    }

    @Override
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public void deleteGroupByName(String name) {
        groupRepository.deleteByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return groupRepository.existsByName(name);
    }
}