package and.fast.simple;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import and.fast.simple.adapters.AlbumAdapter;
import and.fast.simple.entities.ImageEntity;
import and.fast.widget.itemdecorationgroup.AlbumItemDecoration;
import androidx.appcompat.app.AppCompatActivity;
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

        // 必须放置在setAdapter方法后面
        mRecyclerView.addItemDecoration(new AlbumItemDecoration(mRecyclerView));
    }

    public void onStop(View view) {
        int index = new Random().nextInt(DataStore.sImageList.size());
        List<ImageEntity> list = new ArrayList<>();

        for (int i = index; i < DataStore.sImageList.size(); i++) {
            ImageEntity entity = DataStore.sImageList.get(i);
            list.add(new ImageEntity(entity.getTimestamp(), entity.getImageColor()));
        }

        mAdapter.getData().addAll(list);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
    }

}
