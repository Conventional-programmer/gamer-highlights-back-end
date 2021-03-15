package nl.fhict.s6.serviceuser.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.serviceuser.datamodels.UserDao;
import nl.fhict.s6.serviceuser.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDaoConverter extends BaseDaoConverter<UserDao, UserDto> {
    @Override
    public UserDao objectToObjectDao(UserDto object) {
        UserDao userDao = new UserDao();
        userDao.setId(object.getUserId());
        userDao.setUsername(object.getUsername());
        userDao.setPassword(object.getPassword());
        return userDao;
    }

    @Override
    public UserDto objectDaoToObject(UserDao daoObject) {
        UserDto userDto = new UserDto();
        userDto.setUserId(daoObject.getId());
        userDto.setUsername(daoObject.getUsername());
        return userDto;
    }
}
