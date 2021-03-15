package nl.fhict.s6.libraryrest.datamodels;

import javax.persistence.*;

@MappedSuperclass
public class EntityDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public EntityDao() {
    }

    public EntityDao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
