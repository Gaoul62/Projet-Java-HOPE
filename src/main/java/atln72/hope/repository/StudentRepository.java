package atln72.hope.repository;

import atln72.hope.model.StudentToolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentToolEntity, Integer> {

}
