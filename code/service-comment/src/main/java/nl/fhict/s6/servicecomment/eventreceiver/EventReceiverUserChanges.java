package nl.fhict.s6.servicecomment.eventreceiver;

import nl.fhict.s6.servicecomment.Serialization.ISerializer;
import nl.fhict.s6.servicecomment.Serialization.SerializationProvider;
import nl.fhict.s6.servicecomment.basemessaging.EncapsulatingMessage;
import nl.fhict.s6.servicecomment.messaging.EventHandler;
import nl.fhict.s6.servicecomment.event.UsernameChangedEvent;
import nl.fhict.s6.servicecomment.messaging.ServerMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventReceiverUserChanges {
    private Logger log = LoggerFactory.getLogger(EventReceiverUserChanges.class);
    ISerializer<String> jsonSerializer = SerializationProvider.getGsonSerializer();
    private ServerMessageProcessor serverMessageProcessor;

    @Autowired
    public EventReceiverUserChanges(ServerMessageProcessor serverMessageProcessor) {
        this.serverMessageProcessor = serverMessageProcessor;
    }

    @RabbitListener(queues = "${gamehighlights.rabbitmq.queue}")
    public void receive(String event) {
        System.out.println("received the event!");
        log.info("Received event in service document generation: {}", event);
        EncapsulatingMessage encapsulatingMessage = jsonSerializer.deserialize(event, EncapsulatingMessage.class);
        serverMessageProcessor.processMessage(encapsulatingMessage.getMessageType(),encapsulatingMessage.getMessageData());
    }
}
