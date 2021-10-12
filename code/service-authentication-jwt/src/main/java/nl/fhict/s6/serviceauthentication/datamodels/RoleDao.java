package nl.fhict.s6.serviceauthentication.datamodels;


import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType type;

    public RoleDao() {

    }
    public RoleDao(Long id,RoleType roleType) {
        this.id=id;
        this.type =  roleType;
    }


    public RoleDao(RoleType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}
