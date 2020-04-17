package and.fast.simple;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import and.fast.simple.adapters.AlbumAdapter;
import and.fast.simple.data.DataStore;
import and.fast.widget.itemdecorationgroup.AlbumItemDecoration;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements OnRefreshLoadMoreListener {

    private DataStore mDataStore;

    private AlbumAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSmartRefreshLayout = findViewById(R.id.smart_refresh_layout);
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new AlbumAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 必须放置在setAdapter方法后面
        mRecyclerView.addItemDecoration(new AlbumItemDecoration(mRecyclerView));

        // 数据
        mDataStore = new DataStore();
        mDataStore.refresh(mAdapter, mSmartRefreshLayout);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mDataStore.refresh(mAdapter, mSmartRefreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mDataStore.loadMore(mAdapter, mSmartRefreshLayout);
    }

}
