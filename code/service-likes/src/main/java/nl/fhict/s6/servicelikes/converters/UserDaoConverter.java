package nl.fhict.s6.servicelikes.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.servicelikes.datamodels.UserDao;
import nl.fhict.s6.servicelikes.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDaoConverter extends BaseDaoConverter<UserDao, UserDto> {
    @Override
    public UserDao objectToObjectDao(UserDto userDto) {
        UserDao userDao = new UserDao();
        userDao.setId(userDto.getUserId());
        userDao.setUsername(userDto.getUsername());
        return null;
    }

    @Override
    public UserDto objectDaoToObject(UserDao userDao) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userDao.getId());
        userDto.setUsername(userDao.getUsername());
        return userDto;
    }
}
