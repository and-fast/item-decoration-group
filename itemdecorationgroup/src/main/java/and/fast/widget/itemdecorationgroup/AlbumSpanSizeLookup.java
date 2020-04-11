package and.fast.widget.itemdecorationgroup;

import java.util.List;

import and.fast.widget.itemdecorationgroup.model.SpanSizeModel;
import and.fast.widget.itemdecorationgroup.model.ModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private RecyclerView mRecyclerView;

    public AlbumSpanSizeLookup(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        registerDataObserver(recyclerView.getAdapter());
    }

    @Override
    public int getSpanSize(int position) {
        RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
        ModelProvider provider = (ModelProvider) adapter;
        return provider.getModels().get(position).getSpanSize();
    }


    private void registerDataObserver(RecyclerView.Adapter adapter){
        final ModelProvider provider = (ModelProvider) adapter;
        final GridLayoutManager layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                onChanged();
            }

            @Override
            public void onChanged() {
                arrangement(layoutManager.getSpanCount(), provider.getModels());
            }

        });
    }


    private void arrangement(int spanCount, List<? extends SpanSizeModel> modelList) {
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
        }
    }

}
