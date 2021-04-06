package nl.fhict.s6.servicecomment.messaging;

import nl.fhict.s6.servicecomment.basemessaging.IMessageHandler;
import nl.fhict.s6.servicecomment.basemessaging.IMessageHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandler implements IMessageHandlerFactory {
    Map<String,IMessageHandler> messageHandlersByClasses = new HashMap<>();

    @Autowired
    public EventHandler(UserChangesHandler userChangesHandler) {
        messageHandlersByClasses.put("UsernameChangedEvent",userChangesHandler);
    }
    @Override
    public IMessageHandler getHandler(String simpleName) {
        return  messageHandlersByClasses.getOrDefault(simpleName,null);
    }
}
