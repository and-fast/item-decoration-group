package and.fast.simple.entities;

public class ImageEntity {

    private long timestamp;
    private int imageColor;

    public ImageEntity(long timestamp, int imageColor) {
        this.timestamp = timestamp;
        this.imageColor = imageColor;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getImageColor() {
        return imageColor;
    }
}
