package atln72.hope.repository;

import atln72.hope.model.UserAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UserAppEntity, Integer> {

    UserAppEntity findByUsername(String username);
}
