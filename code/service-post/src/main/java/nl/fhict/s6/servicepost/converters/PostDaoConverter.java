package nl.fhict.s6.servicepost.converters;

import nl.fhict.s6.libraryrest.converters.BaseDaoConverter;
import nl.fhict.s6.servicepost.datamodels.PostDao;
import nl.fhict.s6.servicepost.dto.PostDto;
import org.springframework.stereotype.Component;

@Component
public class PostDaoConverter extends BaseDaoConverter<PostDao, PostDto> {
    @Override
    public PostDao objectToObjectDao(PostDto object) {
        PostDao postDao = new PostDao();
        postDao.setId(object.getId());
        postDao.setDescription(object.getDescription());
        postDao.setImageUrl(object.getImageUrl());
        //postDao.setLikes(object.getLikes());
        return postDao;
    }

    @Override
    public PostDto objectDaoToObject(PostDao daoObject) {
        PostDto postDto = new PostDto();
        postDto.setId(daoObject.getId());
        postDto.setImageUrl(daoObject.getImageUrl());
        //postDto.setLikes(daoObject.getLikes());
        postDto.setDescription(daoObject.getDescription());
        return postDto;
    }
}
