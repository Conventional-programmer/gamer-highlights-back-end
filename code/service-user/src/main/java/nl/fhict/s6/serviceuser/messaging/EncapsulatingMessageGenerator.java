package nl.fhict.s6.serviceuser.messaging;


import nl.fhict.s6.serviceuser.Serialization.ISerializer;
import nl.fhict.s6.serviceuser.Serialization.SerializationProvider;
import org.springframework.stereotype.Component;

@Component
public class EncapsulatingMessageGenerator implements IEncapsulatingMessageGenerator{
    ISerializer<String> ser = SerializationProvider.getGsonSerializer();

    public <T> EncapsulatingMessage generateMessage(T content)
    {
        String messageForServerJson = ser.serialize(content);
        String clazz = content.getClass().getName();
        return new EncapsulatingMessage(clazz, messageForServerJson);
    }

    public <T> String generateMessageString(T content)
    {
        EncapsulatingMessage msg = generateMessage(content);
        return ser.serialize(msg);
    }
}
