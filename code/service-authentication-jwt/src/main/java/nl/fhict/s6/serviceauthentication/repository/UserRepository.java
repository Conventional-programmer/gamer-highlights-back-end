package nl.fhict.s6.serviceauthentication.repository;

import nl.fhict.s6.serviceauthentication.datamodels.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao,Long> {
    boolean existsById(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<UserDao> findByUsername(String username);
    Optional<UserDao> findByEmail(String email);
}
