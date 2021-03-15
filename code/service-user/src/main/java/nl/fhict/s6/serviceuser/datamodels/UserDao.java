package nl.fhict.s6.serviceuser.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;

import javax.persistence.Column;

public class UserDao extends EntityDao {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    String password;

    public UserDao() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
