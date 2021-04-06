package nl.fhict.s6.servicecomment.basemessaging;

import nl.fhict.s6.servicecomment.Serialization.ISerializer;
import nl.fhict.s6.servicecomment.Serialization.SerializationProvider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandlerBase<T> implements IMessageHandler {

    public void handleMessage(String data)
    {
        ISerializer<String> ser = SerializationProvider.getGsonSerializer();
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T msg =  ser.deserialize(data, type);
        handleMessageInternal(msg);
    }

    public abstract void handleMessageInternal(T message);

}
