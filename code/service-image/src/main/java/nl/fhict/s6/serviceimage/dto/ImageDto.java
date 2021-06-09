package nl.fhict.s6.serviceimage.dto;


public class ImageDto {
    private ContentType imageType;

    public ImageDto() {
    }

    public ImageDto(ContentType imageType) {
        this.imageType = imageType;
    }

    public ContentType getImageType() {
        return imageType;
    }

    public void setImageType(ContentType imageType) {
        this.imageType = imageType;
    }
}
