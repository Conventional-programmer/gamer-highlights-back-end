package nl.fhict.s6.servicecomment.messaging;

import nl.fhict.s6.libraryrest.exception.NoObjectById;
import nl.fhict.s6.servicecomment.basemessaging.MessageHandlerBase;
import nl.fhict.s6.servicecomment.converters.UserEventConverter;
import nl.fhict.s6.servicecomment.datamodels.UserDao;
import nl.fhict.s6.servicecomment.event.UsernameChangedEvent;
import nl.fhict.s6.servicecomment.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserChangesHandler extends MessageHandlerBase<UsernameChangedEvent> {
    private UserService USER_SERVICE;
    private UserEventConverter USER_EVENT_CONVERTER;

    public UserChangesHandler(UserService USER_SERVICE, UserEventConverter USER_EVENT_CONVERTER) {
        this.USER_SERVICE = USER_SERVICE;
        this.USER_EVENT_CONVERTER = USER_EVENT_CONVERTER;
    }

    @Override
    public void handleMessageInternal(UsernameChangedEvent message) {
        UserDao userDao = USER_EVENT_CONVERTER.userDaoByUserEvent(message);
        try {
            USER_SERVICE.update(userDao);
        } catch (NoObjectById noObjectById) {
            noObjectById.printStackTrace();
        }
    }
}
