package nl.fhict.s6.serviceimage.controller;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
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

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(
            value = "/{content-type}/{image}"
    )
    public ResponseEntity<byte[]> getImageWithMediaType(@PathVariable("content-type")ContentType contentType , @PathVariable("image") String imageName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        String path = String.format("/static/%s/%s.jpg",contentType.name().toLowerCase(),imageName);
        URL imageUrl = getClass().getResource(path);
        InputStream in = imageUrl.openStream();
        byte[] media = IOUtils.toByteArray(in);
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }
    @PostMapping(value = "")
    public ResponseEntity uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty())
        {
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            File destination = new File("/resources/static/");
            ImageIO.write(src, "jpg", destination);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @Bean
    @Profile("dev")
    public ImageService getImageJpaService()
    {
        return new ImageJpaService();
    }
}
