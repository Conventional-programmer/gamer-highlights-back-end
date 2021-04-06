package nl.fhict.s6.servicecomment.converters;

import nl.fhict.s6.servicecomment.datamodels.UserDao;
import nl.fhict.s6.servicecomment.event.UsernameChangedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserEventConverter {
    public UserEventConverter() {
    }

    public UserDao userDaoByUserEvent(UsernameChangedEvent usernameChangedEvent)
    {
        UserDao userDao = new UserDao();
        userDao.setId(usernameChangedEvent.getUserId());
        userDao.setUsername(usernameChangedEvent.getUsername());
        return userDao;
    }
}
