package nl.fhict.s6.servicecomment.messaging;

import nl.fhict.s6.servicecomment.basemessaging.IMessageHandler;
import nl.fhict.s6.servicecomment.basemessaging.MessageProcessorBase;
import nl.fhict.s6.servicecomment.basemessaging.IMessageHandlerFactory;
import nl.fhict.s6.servicecomment.basemessaging.IMessageProcessor;
import org.springframework.stereotype.Component;

@Component
public class ServerMessageProcessor extends MessageProcessorBase implements IMessageProcessor {
    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory) {
        super(messageHandlerFactory);
    }

    @Override
    public void processMessage(String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];
        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType);
        if (handler != null) {
            handler.handleMessage(data);
        } else {
            System.out.println("A message came in but there was no message handler for it");
        }
    }
}
