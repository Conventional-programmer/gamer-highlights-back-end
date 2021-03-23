package nl.fhict.s6.servicecomment.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDao extends EntityDao {
    public String username;

    public UserDao() {
    }

    public UserDao(Long id, String username) {
        super(id);
        this.username = username;
    }

}
