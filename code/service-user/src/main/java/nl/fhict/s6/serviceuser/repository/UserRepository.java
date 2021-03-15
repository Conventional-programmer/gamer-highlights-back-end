package nl.fhict.s6.serviceuser.repository;

import nl.fhict.s6.serviceuser.datamodels.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao,Long> {
}
