package atln72.hope.service;

import atln72.hope.model.StudentToolEntity;
import atln72.hope.repository.StudentToolRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service

public class StudentToolService {
    @Autowired
    private final StudentToolRepository studentRepository;


    public StudentToolService(StudentToolRepository studentToolRepository) {
        this.studentRepository = studentToolRepository;
    }

    public List<StudentToolEntity> getAll() {
        return studentRepository.findAll();
    }

    public Optional<StudentToolEntity> getById(int id) {
        return studentRepository.findById(id);
    }

    public void create(StudentToolEntity studentToolEntity) {
        if (parameterMissing(studentToolEntity))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        
        Optional<StudentToolEntity> studentToolEntityOptional = this.getById(studentToolEntity.getToolId());
        if (studentToolEntityOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool already exists");

        studentRepository.save(studentToolEntity);
    }

    public void update(StudentToolEntity studentToolBody) {
        if (parameterMissing(studentToolBody))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");

        Optional<StudentToolEntity> studentToolEntityOptional = this.getById(studentToolBody.getToolId());
        if (studentToolEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool not found");

        StudentToolEntity studentToolEntity = studentToolEntityOptional.get();
        BeanUtils.copyProperties(studentToolBody, studentToolEntity);
        studentRepository.save(studentToolEntity);
    }

    public void delete(Integer id) {
        if (this.getById(id) == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool not found");

        studentRepository.deleteById(id);
    }

    private boolean parameterMissing(StudentToolEntity studentToolEntity) {
        return studentToolEntity.getTitle() == null || studentToolEntity.getDomainName() == null || studentToolEntity.getDetailedDesc() == null || studentToolEntity.getLink() == null || studentToolEntity.getAccessInstruction() == null;
    }
}