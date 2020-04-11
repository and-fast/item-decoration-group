package and.fast.simple;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import and.fast.simple.adapters.AlbumAdapter;
import and.fast.simple.entities.ImageEntity;
import and.fast.widget.itemdecorationgroup.AlbumItemDecoration;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private AlbumAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new AlbumAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 必须放置在setAdapter方法后面
        mRecyclerView.addItemDecoration(new AlbumItemDecoration(mRecyclerView));
    }

    public void onStop(View view) {
        int index = new Random().nextInt(DataStore.sImageList.size());
        List<ImageEntity> list = new ArrayList<>();

        //for (int i = index; i < DataStore.sImageList.size(); i++) {
            ImageEntity entity = DataStore.sImageList.get(index);
            list.add(new ImageEntity(entity.getTimestamp(), entity.getImageColor()));
       // }

        int itemCount = mAdapter.getItemCount();
        mAdapter.getData().addAll(list);
//        mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRangeInserted(itemCount, mAdapter.getItemCount());
        mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
    }

}
