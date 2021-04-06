package nl.fhict.s6.servicelikes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fhict.s6.servicelikes.converters.LikeDaoConverter;
import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.dto.LikeDto;
import nl.fhict.s6.servicelikes.repository.LikeRepository;
import nl.fhict.s6.servicelikes.service.LikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(LikeController.class)
@ActiveProfiles("test")
public class LikeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    LikeService likeService;
    @Autowired
    LikeDaoConverter realLikeDaoConverter;
    @MockBean
    LikeDaoConverter likeDaoConverter;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getLike() throws Exception {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(1L);
        likeDao.setLikes(300);
        when(likeService.getLikesByPostId(1L)).thenReturn(likeDao.getLikes());
        MvcResult mvcResult = this.mockMvc.perform(get("/like/1")).andReturn();
        assertTrue(mvcResult.getResponse().getStatus()>=200&&mvcResult.getResponse().getStatus()<=300);
        assertEquals(likeDao.getLikes().toString(),mvcResult.getResponse().getContentAsString());
    }
    @Test
    void postLike() throws Exception {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(1L);
        likeDao.setLikes(300);
        LikeDto likeDto = realLikeDaoConverter.objectDaoToObject(likeDao);
        when(likeDaoConverter.objectToObjectDao(likeDto)).thenReturn(likeDao);
        MvcResult mvcResult = this.mockMvc.perform(post("/like").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(likeDao))).andReturn();
        assertTrue(mvcResult.getResponse().getStatus()>=200&&mvcResult.getResponse().getStatus()<=300);
    }
    @Test
    void putLike() throws Exception {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(1L);
        likeDao.setLikes(300);
        LikeDto likeDto = realLikeDaoConverter.objectDaoToObject(likeDao);
        when(likeDaoConverter.objectToObjectDao(likeDto)).thenReturn(likeDao);
        String uri = String.format("/like/%d",likeDao.getPostId());
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(likeDto));
        MvcResult mvcResult = this.mockMvc.perform(builder).andReturn();
        assertTrue(mvcResult.getResponse().getStatus()>=200&&mvcResult.getResponse().getStatus()<=300);
    }
}
