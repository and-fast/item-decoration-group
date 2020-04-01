package and.fast.widget.itemdecorationgroup;

import and.fast.widget.itemdecorationgroup.model.TimestampProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private RecyclerView mRecyclerView;

    public AlbumSpanSizeLookup(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public int getSpanSize(int position) {
        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        TimestampProvider provider = (TimestampProvider) adapter;
        return provider.getSpanSize(position);
    }

}
