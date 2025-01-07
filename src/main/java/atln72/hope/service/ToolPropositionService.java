package atln72.hope.service;

import atln72.hope.model.ToolPropositionEntity;
import atln72.hope.repository.ToolPropositionRepository;

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
public class ToolPropositionService {
    private static final Logger logger = Logger.getLogger(ToolPropositionService.class.getName());

    @Autowired
    private final ToolPropositionRepository toolPropositionRepository;

    public ToolPropositionService(ToolPropositionRepository repository) {
        this.toolPropositionRepository = repository;
    }

    public List<ToolPropositionEntity> getAll() {
        logger.log(Level.INFO, "Fetching all Propositions");
        return toolPropositionRepository.findAll();
    }

    public Optional<ToolPropositionEntity> getById(int id) {
        logger.log(Level.INFO, "Fetching Proposition by id: {0}", id);
        return toolPropositionRepository.findById(id);
    }

    public void create(ToolPropositionEntity proposition) {
        if (parameterMissing(proposition)) {
            logger.log(Level.SEVERE, "Parameters missing while creating Proposition");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }
        Optional<ToolPropositionEntity> propositionEntity = this.getById(proposition.getToolPropositionId());
        if (propositionEntity.isPresent()) {
            logger.log(Level.WARNING, "Proposition already exists with ID: {0}", proposition.getToolPropositionId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Proposition already exists");
        }
        toolPropositionRepository.save(proposition);
        logger.log(Level.INFO, "Proposition created with ID: {0}", proposition.getToolPropositionId());
    }

    public void update(ToolPropositionEntity propositionBody) {
        if (parameterMissing(propositionBody)) {
            logger.log(Level.SEVERE, "Parameters missing while updating Proposition");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }

        Optional<ToolPropositionEntity> existingProposition = this.getById(propositionBody.getToolPropositionId());
        if (existingProposition.isEmpty()) {
            logger.log(Level.WARNING, "Proposition not found with ID: {0}", propositionBody.getToolPropositionId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposition not found");
        }

        ToolPropositionEntity existingEntity = existingProposition.get();
        BeanUtils.copyProperties(propositionBody, existingEntity, "toolPropositionId"); // ID must remain the same
        toolPropositionRepository.save(existingEntity);
        logger.log(Level.INFO, "Proposition updated with ID: {0}", existingEntity.getToolPropositionId());
    }

    public void delete(int id) {
        if (this.getById(id).isEmpty()) {
            logger.log(Level.WARNING, "Proposition not found with ID: {0}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposition not found");
        }
        toolPropositionRepository.deleteById(id);
        logger.log(Level.INFO, "Proposition deleted with ID: {0}", id);
    }

    private boolean parameterMissing(ToolPropositionEntity proposition) {
        return proposition.getSimpleDesc() == null || proposition.getSimpleDesc().isEmpty() || proposition.getDetailedDesc() == null || proposition.getDetailedDesc().isEmpty();
    }

}


