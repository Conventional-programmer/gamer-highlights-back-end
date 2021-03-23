package nl.fhict.s6.servicecomment.repository;

import nl.fhict.s6.servicecomment.datamodels.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDao,Long> {
}
