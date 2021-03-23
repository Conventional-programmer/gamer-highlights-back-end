package nl.fhict.s6.servicecomment.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import nl.fhict.s6.servicecomment.dto.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentDaoConverter extends BaseDaoConverter<CommentDao, CommentDto> {
    @Override
    public CommentDao objectToObjectDao(CommentDto object) {
        CommentDao commentDao = new CommentDao();
        commentDao.setId(object.getId());
        commentDao.setComment(object.getComment());
        return commentDao;
    }

    @Override
    public CommentDto objectDaoToObject(CommentDao daoObject) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(daoObject.getId());
        commentDto.setComment(daoObject.getComment());
        return commentDto;
    }
}
