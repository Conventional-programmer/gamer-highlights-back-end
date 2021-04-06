package nl.fhict.s6.serviceauthentication.repository;

import nl.fhict.s6.serviceauthentication.datamodels.RoleDao;
import nl.fhict.s6.serviceauthentication.datamodels.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleDao, Long> {
    Optional<RoleDao> findByType(RoleType roleType);
}
