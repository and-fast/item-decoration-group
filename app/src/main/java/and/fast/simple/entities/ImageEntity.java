package and.fast.simple.entities;

import and.fast.widget.itemdecorationgroup.model.SpanSizeModel;

public class ImageEntity implements SpanSizeModel {

    private long timestamp;
    private int imageColor;
    private int spanSize;

    public ImageEntity(long timestamp, int imageColor) {
        this.timestamp = timestamp;
        this.imageColor = imageColor;
    }

    @Override
    public int getSpanSize() {
        return spanSize;
    }

    @Override
    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public int getImageColor() {
        return imageColor;
    }

}
