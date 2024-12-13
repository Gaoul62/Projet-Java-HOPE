package atln72.hope.repository;

import atln72.hope.model.ToolPropositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolPropositionRepository extends JpaRepository<ToolPropositionEntity, Integer> {

}
