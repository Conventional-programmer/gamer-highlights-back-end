import nl.fhict.s6.libraryrest.datamodels.EntityDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BaseRepositoryTest<I extends EntityDao> {
    private JpaRepository<I,Long> jpaRepository;
    I objectToSave;
    @Autowired
    TestEntityManager entityManager;
    public BaseRepositoryTest(JpaRepository jpaRepository,I objectToSave) {
        this.jpaRepository = jpaRepository;
    }

    @Test
    void getById() {
        entityManager.merge(objectToSave);
        Optional<I> found = jpaRepository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals(found.get().getId(),1L);
    }
}
