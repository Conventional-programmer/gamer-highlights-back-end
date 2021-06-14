package nl.fhict.s6.servicecomment.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import nl.fhict.s6.servicecomment.dto.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentDaoConverter extends BaseDaoConverter<CommentDao, CommentDto> {
    private UserDaoConverter userDaoConverter;

    public CommentDaoConverter(UserDaoConverter userDaoConverter) {
        this.userDaoConverter = userDaoConverter;
    }

    @Override
    public CommentDao objectToObjectDao(CommentDto object) {
        CommentDao commentDao = new CommentDao();
        commentDao.setId(object.getId());
        commentDao.setUserDao(userDaoConverter.objectToObjectDao(object.getUser()));
        commentDao.setComment(object.getMessage());
        return commentDao;
    }

    @Override
    public CommentDto objectDaoToObject(CommentDao daoObject) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(daoObject.getId());
        commentDto.setUser(userDaoConverter.objectDaoToObject(daoObject.getUserDao()));
        commentDto.setMessage(daoObject.getComment());
        return commentDto;
    }
}
