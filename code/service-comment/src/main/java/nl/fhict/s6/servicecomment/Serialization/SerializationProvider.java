package nl.fhict.s6.servicecomment.Serialization;

public class SerializationProvider {

    private static ISerializer serializer;

    public static ISerializer getGsonSerializer()
    {
        if(serializer == null) serializer = new GsonSerializer();
        return serializer;
    }
}
