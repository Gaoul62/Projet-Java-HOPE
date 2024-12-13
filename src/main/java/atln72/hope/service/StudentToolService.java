package atln72.hope.service;

import atln72.hope.model.StudentToolEntity;
import atln72.hope.repository.StudentToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StudentToolService {
    @Autowired
    private final StudentToolRepository studentRepository;


    public StudentToolService(StudentToolRepository studentToolRepository) {
        this.studentRepository = studentToolRepository;
    }

    public List<StudentToolEntity> getAllTools() {
        return studentRepository.findAll();
    }
}