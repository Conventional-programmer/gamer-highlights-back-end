package nl.fhict.s6.serviceauthentication.config.generation;

import nl.fhict.s6.serviceauthentication.datamodels.RoleDao;
import nl.fhict.s6.serviceauthentication.datamodels.RoleType;

import java.util.ArrayList;
import java.util.List;

public class RoleDaoGeneration {
    public List<RoleDao> generateRoles()
    {
        List<RoleDao> roleDaos = new ArrayList<>();
        for(RoleType roleType: RoleType.values())
        {
            roleDaos.add(new RoleDao(roleType));
        }
        return roleDaos;
    }

}
