package and.fast.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import and.fast.simple.entities.ImageEntity;
import and.fast.widget.itemdecorationgroup.AlbumItemDecoration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private List<ImageEntity> mImageList = Arrays.asList(
            // 2010-04-01
            new ImageEntity(1270087788000L, Color.DKGRAY),
//            new ImageEntity(1270087788000L, Color.DKGRAY),

            // 2020-01-01
            new ImageEntity(1577808000000L, Color.BLACK),
//            new ImageEntity(1577808000000L, Color.BLACK),
//            new ImageEntity(1577808000000L, Color.BLACK),
//            new ImageEntity(1577808000000L, Color.BLACK),
//            new ImageEntity(1577808000000L, Color.BLACK),

            // 2020-02-20
            new ImageEntity(1582188504000L, Color.DKGRAY),
//            new ImageEntity(1582188504000L, Color.DKGRAY),
//            new ImageEntity(1582188504000L, Color.DKGRAY),
//            new ImageEntity(1582188504000L, Color.DKGRAY),
//            new ImageEntity(1582188504000L, Color.DKGRAY),
//            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),

            // 2020-05-20
//            new ImageEntity(1589964504000L, Color.GRAY),
//            new ImageEntity(1589964504000L, Color.GRAY),
//            new ImageEntity(1589964504000L, Color.GRAY),
//            new ImageEntity(1589964504000L, Color.GRAY),
//            new ImageEntity(1589964504000L, Color.GRAY),
//            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),

            // 2020-08-20
//            new ImageEntity(1597913304000L, Color.LTGRAY),
//            new ImageEntity(1597913304000L, Color.LTGRAY),
//            new ImageEntity(1597913304000L, Color.LTGRAY),
//            new ImageEntity(1597913304000L, Color.LTGRAY),
//            new ImageEntity(1597913304000L, Color.LTGRAY),
//            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY)
    );

    private AlbumAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new AlbumItemDecoration(recyclerView));
        mAdapter = new AlbumAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    public void onStop(View view) {
        int index = new Random().nextInt(mImageList.size());
        List<ImageEntity> list = new ArrayList<>();

        for (int i = index; i < mImageList.size(); i++) {
            ImageEntity entity = mImageList.get(i);
            list.add(new ImageEntity(entity.getTimestamp(), entity.getImageColor()));
        }

        mAdapter.getData().addAll(list);
        mAdapter.notifyDataSetChanged();
    }

}
