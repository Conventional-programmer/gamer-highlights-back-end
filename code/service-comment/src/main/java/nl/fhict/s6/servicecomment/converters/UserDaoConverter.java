package nl.fhict.s6.servicecomment.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.libraryrest.converters.DaoConverter;
import nl.fhict.s6.servicecomment.datamodels.UserDao;
import nl.fhict.s6.servicecomment.dto.UserDto;

public class UserDaoConverter extends BaseDaoConverter<UserDao, UserDto> {
    @Override
    public UserDao objectToObjectDao(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto objectDaoToObject(UserDao userDao) {
        return null;
    }
}
