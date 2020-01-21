package and.fast.widget.itemdecorationgroup;

import android.util.Log;

import and.fast.widget.itemdecorationgroup.model.TimestampProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private int index;
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
        Log.i("AND-FAST", "------- (" + position + ") -------");
        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        TimestampProvider provider = (TimestampProvider) adapter;

        long timestamp = provider.getTimestamp(position);

        if (position < adapter.getItemCount() - 1) {
            long nextTimestamp = provider.getTimestamp(position + 1);
            if (timestamp != nextTimestamp) {
                //int number = mSpanCount - (index % mSpanCount);
                //Log.i("AND-FAST", number + ", " + index);
                //index = 0;

                return mSpanCount;
            }
        }

        //index = index + 1;
        return 1;
    }

}
