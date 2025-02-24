package idespring.lab2.service.subjectservice;

import idespring.lab2.model.Subject;
import idespring.lab2.repository.subjectrepo.SubjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> readSubjects(String namePattern, String sort) {
        if (namePattern != null) {
            return subjectRepository.findByNameContaining(namePattern);
        }
        if (sort != null && sort.equalsIgnoreCase("asc")) {
            return subjectRepository.findAllByOrderByNameAsc();
        }
        return subjectRepository.findAll();
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return subjectRepository.existsByName(name);
    }
}
