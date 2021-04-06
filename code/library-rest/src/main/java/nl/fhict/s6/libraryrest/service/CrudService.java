package nl.fhict.s6.libraryrest.service;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;
import nl.fhict.s6.libraryrest.exception.NoObjectById;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public class CrudService<T extends EntityDao> {
    private JpaRepository<T,Long> jpaRepository;

    public CrudService(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    public List<T> findAll()
    {
        return jpaRepository.findAll();
    }
    public T findById(Long id)
    {
        Optional<T> optionalResult = this.jpaRepository.findById(id);
        return optionalResult.isPresent() ? optionalResult.get() : null;
    }
    public T save(T object)
    {
        return jpaRepository.save(object);
    }
    public T update(T object) throws NoObjectById {
        if(jpaRepository.existsById(object.getId()))
        {
            return jpaRepository.save(object);
        }
        throw new NoObjectById("does not exist");
    }
    public void deleteById(Long id)
    {
        jpaRepository.deleteById(id);
    }
}
