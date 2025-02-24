package idespring.lab2.repository.subjectrepo;

import idespring.lab2.model.Subject;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);

    List<Subject> findByNameContaining(String namePattern);

    boolean existsByName(String name);

    void deleteByName(String name);

    List<Subject> findAllByOrderByNameAsc();
}
