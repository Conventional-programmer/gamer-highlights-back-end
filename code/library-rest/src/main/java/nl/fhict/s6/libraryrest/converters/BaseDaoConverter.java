package nl.fhict.s6.libraryrest.converters;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoConverter<Dao, Dto> implements DaoConverter<Dao, Dto> {
    public List<Dto> objectDaosToObjects(Iterable<Dao> objectDaos)
    {
        List<Dto> objects = new ArrayList<>();
        for (Dao objectDao : objectDaos) {
            objects.add(objectDaoToObject(objectDao));
        }
        return objects;
    }
    public List<Dao> objectsToObjectDaos(Iterable<Dto> objects)
    {
        List<Dao> objectDaos = new ArrayList<>();
        for (Dto object : objects) {
            objectDaos.add(objectToObjectDao(object));
        }
        return objectDaos;
    }
}
