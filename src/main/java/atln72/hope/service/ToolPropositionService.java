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

@Service
public class ToolPropositionService {
    @Autowired
    private final ToolPropositionRepository toolPropositionRepository;

    public ToolPropositionService(ToolPropositionRepository repository) {
        this.toolPropositionRepository = repository;
    }

    public List<ToolPropositionEntity> getAll() {
        return toolPropositionRepository.findAll();
    }

    public Optional<ToolPropositionEntity> getById(int id) {
        return toolPropositionRepository.findById(id);
    }

    public void create(ToolPropositionEntity proposition) {
        if (parameterMissing(proposition)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }

        toolPropositionRepository.save(proposition);
    }

    public void update(ToolPropositionEntity propositionBody) {
        if (parameterMissing(propositionBody)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters missing");
        }

        Optional<ToolPropositionEntity> existingProposition = this.getById(propositionBody.getToolPropositionId());
        if (existingProposition.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tool proposition not found");
        }

        ToolPropositionEntity existingEntity = existingProposition.get();
        BeanUtils.copyProperties(propositionBody, existingEntity, "toolPropositionId"); // ID must remain the same
        toolPropositionRepository.save(existingEntity);
    }

    public void delete(int id) {
        if (this.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tool proposition not found");
        }
        toolPropositionRepository.deleteById(id);
    }

    private boolean parameterMissing(ToolPropositionEntity proposition) {
        return proposition.getSimpleDesc() == null || proposition.getSimpleDesc().isEmpty() || proposition.getDetailedDesc() == null || proposition.getDetailedDesc().isEmpty();
    }

}


