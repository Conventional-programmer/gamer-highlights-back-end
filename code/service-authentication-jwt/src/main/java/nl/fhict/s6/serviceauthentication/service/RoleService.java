package nl.fhict.s6.serviceauthentication.service;

import nl.fhict.s6.serviceauthentication.datamodels.RoleDao;
import nl.fhict.s6.serviceauthentication.datamodels.RoleType;
import nl.fhict.s6.serviceauthentication.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RoleService {
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Optional<RoleDao> findByType(RoleType roleType)
    {
        return roleRepository.findByType(roleType);
    }

    public List<RoleDao> getAllRoles()
    {
        return roleRepository.findAll();
    }
}
