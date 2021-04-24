package nl.fhict.s6.servicecomment.config.generation;

import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import nl.fhict.s6.servicecomment.datamodels.UserDao;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoGeneration {
    public List<CommentDao> generateCommentDaos(List<UserDao> userDaos)
    {
        List<CommentDao> commentDaos = new ArrayList<>();
        commentDaos.add(new CommentDao(1L,userDaos.get(0),"How nice!"));
        commentDaos.add(new CommentDao(2L,userDaos.get(0),"How cool!"));
        return commentDaos;
    }
}
