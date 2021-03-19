package nl.fhict.s6.servicelikes.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.dto.LikeDto;
import org.springframework.stereotype.Component;

@Component
public class LikeDaoConverter extends BaseDaoConverter<LikeDao, LikeDto> {
    @Override
    public LikeDao objectToObjectDao(LikeDto object) {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(object.getPostId());
        likeDao.setLikes(object.getLikes());
        return likeDao;
    }

    @Override
    public LikeDto objectDaoToObject(LikeDao daoObject) {
        LikeDto likeDto = new LikeDto();
        likeDto.setPostId(daoObject.getPostId());
        likeDto.setLikes(daoObject.getLikes());
        return likeDto;
    }
}
