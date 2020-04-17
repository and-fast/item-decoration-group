package and.fast.simple.data.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import and.fast.widget.itemdecorationgroup.model.SpanSizeModel;

public class ImageEntity implements SpanSizeModel {

    private String       createdAt;
    private String       desc;
    private String       title;
    private String       url;

    private int spanSize;

    public String getUrl() {
        return url;
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
        try {

            long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createdAt).getTime();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
            return new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
