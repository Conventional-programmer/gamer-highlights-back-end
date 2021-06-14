package nl.fhict.s6.servicelikes.controller;

import nl.fhict.s6.servicelikes.converters.LikeDaoConverter;
import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.dto.LikeDto;
import nl.fhict.s6.servicelikes.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/like")
public class LikeController {
    private LikeService likeService;
    private LikeDaoConverter likeDaoConverter;

    public LikeController(LikeService likeService, LikeDaoConverter likeDaoConverter) {
        this.likeService = likeService;
        this.likeDaoConverter = likeDaoConverter;
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<List<LikeDto>> getLikesByPostId(@PathVariable("postId") Long postId)
    {
        List<LikeDao> likeDao = likeService.getLikesByPostId(postId);
        return new ResponseEntity<>(likeDaoConverter.objectDaosToObjects(likeDao), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity saveLike(LikeDto likeDto)
    {
        LikeDao likeDao = likeDaoConverter.objectToObjectDao(likeDto);
        likeService.save(likeDao);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{postId}")
    public ResponseEntity updateLikesOnPost(@PathVariable("postId")Long postId, LikeDto likeDto)
    {
        if(likeDto.getPostId() != postId)
            return ResponseEntity.badRequest().build();
        LikeDao likeDao = likeDaoConverter.objectToObjectDao(likeDto);
        likeService.update(likeDao);
        return ResponseEntity.ok().build();
    }
}
