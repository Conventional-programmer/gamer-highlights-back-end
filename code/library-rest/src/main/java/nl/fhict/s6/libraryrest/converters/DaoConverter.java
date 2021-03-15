package nl.fhict.s6.libraryrest.converters;

import java.util.List;

public interface DaoConverter<Dao, Dto> {
    Dao objectToObjectDao(Dto object);
    Dto objectDaoToObject(Dao daoObject);
    List<Dto> objectDaosToObjects(Iterable<Dao> objectDaos);
    List<Dao> objectsToObjectDaos(Iterable<Dto> objects);
}
