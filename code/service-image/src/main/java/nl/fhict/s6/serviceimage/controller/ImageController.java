package nl.fhict.s6.serviceimage.controller;

import nl.fhict.s6.libraryrest.authentication.http.PermissionHttpHeader;
import nl.fhict.s6.serviceimage.converters.ImageConverter;
import nl.fhict.s6.serviceimage.datamodels.ImageDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import nl.fhict.s6.serviceimage.dto.ImageDto;
import nl.fhict.s6.serviceimage.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public ResponseEntity<byte[]> getImageWithMediaType(PermissionHttpHeader permissionHttpHeader,@PathVariable("content-type")ContentType contentType , @PathVariable("image") String imageName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        ImageDao imageDao = imageService.findByContentTypeAndName(contentType, getSimpleName(imageName));
        System.out.println(imageName);
        System.out.println(contentType);
        if(permissionHttpHeader.getBasePermission().getUserId() <= 0)
        {
            return new ResponseEntity(null,headers,HttpStatus.UNAUTHORIZED);
        }
        if(imageDao == null || !imageDao.getUserId().equals(permissionHttpHeader.getBasePermission().getUserId()))
        {
            System.out.println(imageDao == null);
            return new ResponseEntity(null,headers,HttpStatus.FORBIDDEN);
        }
        Path directoryPath = Paths.get(String.format("images/%s/",contentType.name().toLowerCase()));
        Path filePath = directoryPath.resolve(String.format("%s",imageName));
        byte[] media = FileUtils.readFileToByteArray(filePath.toFile());
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }
    @PostMapping(value = "",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadImage(@RequestHeader(value = "X-Forwarded-Proto", defaultValue = "") String proto ,PermissionHttpHeader permissionHttpHeader, @RequestPart("file") MultipartFile file, @RequestPart("image") ImageDto imageDto) throws IOException {
        System.out.println(proto);
        System.out.println(permissionHttpHeader.getScheme());
        if(!file.isEmpty())
        {
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            ImageDao imageDao = imageConverter.convertDtoToImageDao(imageDto,getSimpleName(file.getOriginalFilename()),permissionHttpHeader.getBasePermission().getUserId());
            imageService.save(imageDao);
            Path directoryPath = Paths.get(String.format("images/%s/",imageDto.getImageType().name().toLowerCase()));
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }
            Path filePath = directoryPath.resolve(file.getOriginalFilename());
            ImageIO.write(src, "jpg", filePath.toFile());
            String serviceUrl = ServletUriComponentsBuilder.fromRequestUri(permissionHttpHeader).replacePath("").replaceQuery("").toUriString();
            if(!proto.isEmpty() && !proto.equals(permissionHttpHeader.getScheme()))
            {
                serviceUrl = serviceUrl.replace(permissionHttpHeader.getScheme(),proto);
            }
            String imageUrl = String.format("%s/image/%s/%s",serviceUrl,imageDto.getImageType().name().toLowerCase(),file.getOriginalFilename());
            return new ResponseEntity<>(imageUrl,HttpStatus.OK);
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
