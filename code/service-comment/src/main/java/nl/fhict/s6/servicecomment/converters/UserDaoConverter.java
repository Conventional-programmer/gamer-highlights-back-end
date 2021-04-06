package nl.fhict.s6.servicecomment.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.servicecomment.datamodels.UserDao;
import nl.fhict.s6.servicecomment.dto.UserDto;

public class UserDaoConverter extends BaseDaoConverter<UserDao, UserDto> {
    @Override
    public UserDao objectToObjectDao(UserDto userDto) {
        UserDao userDao = new UserDao();
        userDao.setId(userDto.getUserId());
        userDao.setUsername(userDto.getUsername());
        return userDao;
    }

    @Override
    public UserDto objectDaoToObject(UserDao userDao) {
        return null;
    }
}
