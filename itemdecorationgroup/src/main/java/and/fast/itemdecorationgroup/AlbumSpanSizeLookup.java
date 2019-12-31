package and.fast.itemdecorationgroup;

import and.fast.itemdecorationgroup.model.TimestampProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int index;
    private int mPrePosition;
    private int mSpanCount; // 数量

    private RecyclerView mRecyclerView;

    public AlbumSpanSizeLookup(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

        mRecyclerView = recyclerView;
        mSpanCount = gridLayoutManager.getSpanCount();
    }

    @Override
    public int getSpanSize(int position) {
        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        TimestampProvider provider = (TimestampProvider) adapter;
        long timestamp = provider.getTimestamp(position);

        if (position < adapter.getItemCount() - 1) { // 判断是否为最后一个条目
            // 获取下一个条目时间
            long nextTimestamp = provider.getTimestamp(position + 1);
            if (timestamp != nextTimestamp) {
                int index = position - mPrePosition;
                mPrePosition = position;

                //int placeholderNumber = mSpanCount - (index % mSpanCount); // 换占位数
//                Log.i(getClass().getSimpleName(),
//                        "position: " + position
//                                + ", index: " + index
//                                + ", number: " + placeholderNumber
//                                + ", result: " + (index == 0 ? mSpanCount : (placeholderNumber + 1)));
                //Log.i(getClass().getSimpleName(), String.valueOf(index));
               return index == 0 ? mSpanCount : (3 + 1);
            }
        }

        index = index + 1; // 计数
        return 1;
    }

}
