package and.fast.widget.itemdecorationgroup.utils;

import java.util.List;

import and.fast.widget.itemdecorationgroup.model.SpanSizeModel;

public class SpanSizeSortUtil {

    public static void sort(int spanCount, List<? extends SpanSizeModel> modelList) {
        int column = 0, index = 0; // 当前行

        for (int i = 0; i < modelList.size() - 1; i++) {
            SpanSizeModel imageEntity = modelList.get(i);
            SpanSizeModel nextImageEntity = modelList.get(i + 1);

            // 如果换行，重置列数
            if (index % spanCount == 0) {
                column = 0;
            }

            // 不相同，换行
            if (imageEntity.getTimestamp() != nextImageEntity.getTimestamp()) {
                imageEntity.setSpanSize(spanCount - column);
                index = index + (spanCount - column);
                column = 0;

            } else {
                ++column;
                ++index;
                imageEntity.setSpanSize(1);
            }

            //Log.i(SpanSizeSortUtil.class.getSimpleName(), i + " , " + imageEntity.getSpanSize());
        }

        //Log.i(SpanSizeSortUtil.class.getSimpleName(), "-----------------");
    }

}
