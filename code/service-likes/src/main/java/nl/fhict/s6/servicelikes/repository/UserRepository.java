package nl.fhict.s6.servicelikes.repository;

import nl.fhict.s6.servicelikes.datamodels.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDao,Long> {
}
