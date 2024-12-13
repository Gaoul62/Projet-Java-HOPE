package atln72.hope.repository;

import atln72.hope.model.UserAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAppEntity, Integer> {

}
