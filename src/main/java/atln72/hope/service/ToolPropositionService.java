package atln72.hope.service;

import atln72.hope.model.ToolPropositionEntity;
import atln72.hope.repository.ToolPropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolPropositionService {
    @Autowired
    private final ToolPropositionRepository toolPropositionRepository;

    public ToolPropositionService(ToolPropositionRepository userRepository) {
        this.toolPropositionRepository = userRepository;
    }

    public List<ToolPropositionEntity> getAllUsers() {
        return toolPropositionRepository.findAll();
    }
}