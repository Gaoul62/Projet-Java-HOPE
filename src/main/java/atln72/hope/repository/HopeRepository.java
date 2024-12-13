package atln72.hope.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<HopeEntity, Integer> {

}
