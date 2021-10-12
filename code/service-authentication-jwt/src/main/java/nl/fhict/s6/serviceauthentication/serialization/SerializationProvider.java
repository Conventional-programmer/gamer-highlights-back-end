package nl.fhict.s6.serviceauthentication.serialization;

public class SerializationProvider {

    private static ISerializer serializer;

    public static ISerializer getGsonSerializer()
    {
        if(serializer == null) serializer = new GsonSerializer();
        return serializer;
    }
}
