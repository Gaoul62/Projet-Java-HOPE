package atln72.hope.service;

import atln72.hope.model.StudentToolEntity;
import atln72.hope.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentToolService {
    private final StudentRepository studentRepository;

    public StudentToolService(StudentRepository studentToolRepository) {
        this.studentRepository = studentToolRepository;
    }

    public List<StudentToolEntity> getAllTools() {
        return studentRepository.findAll();
    }
}