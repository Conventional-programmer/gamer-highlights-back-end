package nl.fhict.s6.servicepost.context;

public class LikeHttpContext extends BaseHttpContext<Integer,Long>{
    public LikeHttpContext(String serverIp, Integer port, String baseUrl) {
        super(serverIp, port, baseUrl);
    }
}
