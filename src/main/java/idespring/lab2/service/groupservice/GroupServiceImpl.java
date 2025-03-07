package idespring.lab2.service.groupservice;

import idespring.lab2.model.Group;
import idespring.lab2.model.Student;
import idespring.lab2.repository.grouprepo.GroupRepository;
import idespring.lab2.repository.studentrepo.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
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
    public Group addGroup(String name, List<Integer> studentIds) {
        Group group = new Group(name);

        if (studentIds != null && !studentIds.isEmpty()) {
            List<Student> students = studentRepository.findAllById(
                    studentIds.stream().map(Long::valueOf).collect(Collectors.toList())
            );
            for (Student student : students) {
                student.setGroup(group);
            }
            group.setStudents(students);
        }

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
}