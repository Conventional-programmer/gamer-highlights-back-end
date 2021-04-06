package nl.fhict.s6.servicecomment.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "User")
@Table(name = "user")
public class UserDao extends EntityDao {
    private String username;

    public UserDao() {
    }

    public UserDao(Long id, String username) {
        super(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
