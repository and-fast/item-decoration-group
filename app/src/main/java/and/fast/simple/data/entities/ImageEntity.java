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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public long getTimestamp() {
        try {

            return new SimpleDateFormat("yyyy-MM").parse(createdAt).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
