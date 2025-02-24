package idespring.lab2.repository.grouprepo;

import idespring.lab2.model.Group;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);

    List<Group> findByNameContaining(String namePattern);

    boolean existsByName(String name);

    void deleteByName(String name);

    List<Group> findAllByOrderByNameAsc();
}
