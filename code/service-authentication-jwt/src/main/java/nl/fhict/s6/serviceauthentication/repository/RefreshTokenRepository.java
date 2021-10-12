package nl.fhict.s6.serviceauthentication.repository;

import nl.fhict.s6.serviceauthentication.datamodels.RefreshTokenDao;
import nl.fhict.s6.serviceauthentication.datamodels.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenDao, Long> {

    @Override
    Optional<RefreshTokenDao> findById(Long id);

    Optional<RefreshTokenDao> findByToken(String token);
    int deleteByUser(UserDao userDao);

}

