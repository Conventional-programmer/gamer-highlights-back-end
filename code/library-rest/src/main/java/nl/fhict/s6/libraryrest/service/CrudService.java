package nl.fhict.s6.libraryrest.service;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;
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
    public void save(T object)
    {
        jpaRepository.save(object);
    }
    public void update(T object)
    {
        if(jpaRepository.existsById(object.getId()))
        {
            jpaRepository.save(object);
        }
    }
    public void deleteById(Long id)
    {
        jpaRepository.deleteById(id);
    }
}
