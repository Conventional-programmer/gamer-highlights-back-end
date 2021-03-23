package nl.fhict.s6.libraryrest.controller;

import nl.fhict.s6.libraryrest.converters.DaoConverter;
import nl.fhict.s6.libraryrest.datamodels.EntityDao;
import nl.fhict.s6.libraryrest.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController <Dao extends EntityDao,Dto> {
    private CrudService<Dao> crudService;
    private DaoConverter<Dao,Dto> daoConverter;
    public BaseController(CrudService crudService,DaoConverter daoConverter) {
        this.crudService = crudService;
        this.daoConverter = daoConverter;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Dto>> getAllEntities()
    {
        List<Dao> daos = crudService.findAll();
        List<Dto> dtoEntities = daoConverter.objectDaosToObjects(daos);
        return new ResponseEntity<>(dtoEntities, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dto> getEntityById(@PathVariable("id") Long id)
    {
        Dao dao = crudService.findById(id);
        Dto dtoEntity = daoConverter.objectDaoToObject(dao);
        return new ResponseEntity<>(dtoEntity, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEntityById(@PathVariable("id") Long id){
        crudService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("")
    public ResponseEntity post(@ModelAttribute() Dto dto)
    {
        Dao dao = daoConverter.objectToObjectDao(dto);
        crudService.save(dao);
        return ResponseEntity.ok().build();
    }
}
