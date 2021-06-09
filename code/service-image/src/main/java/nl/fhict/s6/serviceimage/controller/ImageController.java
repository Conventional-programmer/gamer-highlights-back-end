package nl.fhict.s6.serviceimage.controller;

import nl.fhict.s6.serviceimage.converters.ImageConverter;
import nl.fhict.s6.serviceimage.datamodels.ImageDao;
import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import nl.fhict.s6.serviceimage.dto.ImageDto;
import nl.fhict.s6.serviceimage.service.ImageJpaService;
import nl.fhict.s6.serviceimage.service.ImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Controller
@RequestMapping("/image")
public class ImageController {
    private ImageService imageService;
    private ImageConverter imageConverter;

    public ImageController(ImageService imageService, ImageConverter imageConverter) {
        this.imageService = imageService;
        this.imageConverter = imageConverter;
    }

    @GetMapping(
            value = "/{content-type}/{image}"
    )
    public ResponseEntity<byte[]> getImageWithMediaType(@PathVariable("content-type")ContentType contentType , @PathVariable("image") String imageName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ImageDao imageDao = imageService.findByContentTypeAndName(contentType, imageName);
        if(imageDao == null || imageDao.getUserId() != 1)
        {
            return new ResponseEntity(null,headers,HttpStatus.UNAUTHORIZED);
        }
        String path = String.format("/static/%s/%s.jpg",contentType.name().toLowerCase(),imageName);
        URL imageUrl = getClass().getResource(path);
        InputStream in = imageUrl.openStream();
        byte[] media = IOUtils.toByteArray(in);
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }
    @PostMapping(value = "",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadImage(@RequestPart("file") MultipartFile file,@RequestPart("image") ImageDto imageDto) throws IOException {
        if(!file.isEmpty())
        {
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            ImageDao imageDao = imageConverter.convertDtoToImageDao(imageDto,getSimpleName(file.getOriginalFilename()),1L);
            ImageDao saved  = imageService.save(imageDao);
            String destinationUrl = String.format("src/main/resources/static/%s/%s",imageDto.getImageType().name().toLowerCase(),file.getOriginalFilename());
            File destination = new File(destinationUrl);
            ImageIO.write(src, "jpg", destination);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    private String getSimpleName(String fileName)
    {
        int index = fileName.indexOf('.');
        if(index != -1)
        {
            return fileName.substring(0,index);
        }
        return fileName;
    }
}
