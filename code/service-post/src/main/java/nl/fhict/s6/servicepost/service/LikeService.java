package nl.fhict.s6.servicepost.service;

import nl.fhict.s6.servicepost.context.LikeHttpContext;
import org.springframework.stereotype.Service;

@Service
public class LikeService  extends HttpBaseService<Integer,Long>{
    public LikeService() {
        super(new LikeHttpContext("localhost",5000,"like"));
    }
}
