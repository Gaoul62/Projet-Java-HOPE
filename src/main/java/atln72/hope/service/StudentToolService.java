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
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentToolService {
    private static final Logger logger = Logger.getLogger(StudentToolService.class.getName());

    @Autowired
    private final StudentToolRepository studentRepository;

    public StudentToolService(StudentToolRepository studentToolRepository) {
        this.studentRepository = studentToolRepository;
    }

    public List<StudentToolEntity> getAll() {
        logger.log(Level.INFO, "Fetching all Tools");
        return studentRepository.findAll();
    }

    public Optional<StudentToolEntity> getById(int id) {
        logger.log(Level.INFO, "Fetching Tool by id: {0}", id);
        return studentRepository.findById(id);
    }

    public void create(StudentToolEntity studentToolEntity) {
        if (parameterMissing(studentToolEntity)) {
            logger.log(Level.SEVERE, "Parameters missing while creating Tool");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        
        Optional<StudentToolEntity> studentToolEntityOptional = this.getById(studentToolEntity.getToolId());
        if (studentToolEntityOptional.isPresent()) {
            logger.log(Level.WARNING, "Tool already exists with ID: {0}", studentToolEntity.getToolId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool already exists");
        }
        studentRepository.save(studentToolEntity);
        logger.log(Level.INFO, "Tool created with ID: {0}", studentToolEntity.getToolId());
    }

    public void update(StudentToolEntity studentToolBody) {
        if (parameterMissing(studentToolBody)) {
            logger.log(Level.SEVERE, "Parameters missing while updating Tool");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        Optional<StudentToolEntity> studentToolEntityOptional = this.getById(studentToolBody.getToolId());
        if (studentToolEntityOptional.isEmpty()) {
            logger.log(Level.WARNING, "Tool not found with ID: {0}", studentToolBody.getToolId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool not found");
        }
        StudentToolEntity studentToolEntity = studentToolEntityOptional.get();
        BeanUtils.copyProperties(studentToolBody, studentToolEntity);
        studentRepository.save(studentToolEntity);
        logger.log(Level.INFO, "Tool updated with ID: {0}", studentToolEntity.getToolId());
    }

    public void delete(Integer id) {
        if (this.getById(id) == null) {
            logger.log(Level.WARNING, "Tool not found with ID: {0}", id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool not found");
        }
        studentRepository.deleteById(id);
        logger.log(Level.INFO, "Tool deleted with ID: {0}", id);
    }

    private boolean parameterMissing(StudentToolEntity studentToolEntity) {
        return studentToolEntity.getTitle() == null || studentToolEntity.getDomainName() == null || studentToolEntity.getDetailedDesc() == null || studentToolEntity.getLink() == null || studentToolEntity.getAccessInstruction() == null;
    }
}