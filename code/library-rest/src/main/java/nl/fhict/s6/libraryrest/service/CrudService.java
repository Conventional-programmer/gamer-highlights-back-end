package nl.fhict.s6.libraryrest.service;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;
import nl.fhict.s6.libraryrest.datamodels.Permission;
import nl.fhict.s6.libraryrest.exception.NoObjectById;
import nl.fhict.s6.libraryrest.authentication.http.exception.PermissionDenied;
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
    public List<T> findAll(Permission permission) throws PermissionDenied
    {
        return jpaRepository.findAll();

    }
    public T findById(Long id,Permission permission) throws PermissionDenied
    {
        Optional<T> optionalResult = this.jpaRepository.findById(id);
        return optionalResult.isPresent() ? optionalResult.get() : null;
    }
    public T save(T object, Permission permission) throws PermissionDenied
    {
        return jpaRepository.save(object);
    }
    public T update(T object,Permission permission) throws NoObjectById, PermissionDenied {
        if(!jpaRepository.existsById(object.getId()))
        {
            throw new NoObjectById("does not exist");
        }
        return jpaRepository.save(object);
    }
    public void deleteById(Long id, Permission permission) throws PermissionDenied
    {
        jpaRepository.deleteById(id);
    }
}
