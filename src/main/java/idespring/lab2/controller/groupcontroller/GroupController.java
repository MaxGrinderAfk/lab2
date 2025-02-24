package idespring.lab2.controller.groupcontroller;

import idespring.lab2.model.Group;
import idespring.lab2.service.groupservice.GroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/add")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        if (groupService.existsByName(group.getName())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(groupService.addGroup(group), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Group>> getGroups(
            @RequestParam(required = false) String namePattern,
            @RequestParam(required = false) String sort) {
        List<Group> groups = groupService.readGroups(namePattern, sort);
        return !groups.isEmpty()
                ? new ResponseEntity<>(groups, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{groupId}")
    public ResponseEntity<Void> deleteGroup(@Positive @NotNull @PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}