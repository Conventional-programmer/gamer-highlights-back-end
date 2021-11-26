package nl.fhict.s6.libraryrest.controller;

import nl.fhict.s6.libraryrest.converters.DaoConverter;
import nl.fhict.s6.libraryrest.datamodels.EntityDao;
import nl.fhict.s6.libraryrest.exception.NoObjectById;
import nl.fhict.s6.libraryrest.authentication.http.exception.PermissionDenied;
import nl.fhict.s6.libraryrest.authentication.http.PermissionHttpHeader;
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
    public ResponseEntity<List<Dto>> getAllEntities(PermissionHttpHeader permissionHttpHeader)
    {
        try {
            List<Dao> daos = crudService.findAll(permissionHttpHeader.getBasePermission());
            List<Dto> dtoEntities = daoConverter.objectDaosToObjects(daos);
            return new ResponseEntity<>(dtoEntities, HttpStatus.OK);
        } catch (PermissionDenied permissionDenied) {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Dto> getEntityById(@PathVariable("id") Long id,PermissionHttpHeader permissionHttpHeader)
    {
        try {
            Dao dao  = crudService.findById(id,permissionHttpHeader.getBasePermission());
            Dto dtoEntity = daoConverter.objectDaoToObject(dao);
            return new ResponseEntity<>(dtoEntity, HttpStatus.OK);
        } catch (PermissionDenied permissionDenied) {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEntityById(@PathVariable("id") Long id,PermissionHttpHeader permissionHttpHeader){
        try {
            crudService.deleteById(id,permissionHttpHeader.getBasePermission());
            return ResponseEntity.ok().build();
        } catch (PermissionDenied permissionDenied) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }
    @PostMapping("")
    public ResponseEntity post(@RequestBody Dto dto, PermissionHttpHeader permissionHttpHeader)
    {
        Dao dao = daoConverter.objectToObjectDao(dto);
        try {
            crudService.save(dao, permissionHttpHeader.getBasePermission());
            return ResponseEntity.ok().build();
        } catch (PermissionDenied permissionDenied) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }
    @PutMapping("")
    public ResponseEntity put(@RequestBody Dto dto,PermissionHttpHeader permissionHttpHeader)
    {
        Dao dao = daoConverter.objectToObjectDao(dto);
        try {
            crudService.update(dao,permissionHttpHeader.getBasePermission());
        } catch (NoObjectById noObjectById) {
            return ResponseEntity.badRequest().build();
        } catch (PermissionDenied permissionDenied) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().build();
    }
}
