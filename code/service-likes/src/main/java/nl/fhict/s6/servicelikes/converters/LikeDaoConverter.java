package nl.fhict.s6.servicelikes.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.dto.LikeDto;
import org.springframework.stereotype.Component;

@Component
public class LikeDaoConverter extends BaseDaoConverter<LikeDao, LikeDto> {
    private UserDaoConverter userDaoConverter;

    public LikeDaoConverter(UserDaoConverter userDaoConverter) {
        this.userDaoConverter = userDaoConverter;
    }

    @Override
    public LikeDao objectToObjectDao(LikeDto object) {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(object.getPostId());
        likeDao.setUserDao(userDaoConverter.objectToObjectDao(object.getUserDto()));
        return likeDao;
    }

    @Override
    public LikeDto objectDaoToObject(LikeDao daoObject) {
        LikeDto likeDto = new LikeDto();
        likeDto.setId(daoObject.getId());
        likeDto.setPostId(daoObject.getPostId());
        likeDto.setUserDto(userDaoConverter.objectDaoToObject(daoObject.getUserDao()));
        return likeDto;
    }
}
