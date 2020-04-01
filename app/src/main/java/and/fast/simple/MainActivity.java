package and.fast.simple;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import and.fast.simple.entities.ImageEntity;
import and.fast.widget.itemdecorationgroup.AlbumItemDecoration;
import and.fast.widget.itemdecorationgroup.utils.SpanSizeSortUtil;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private List<ImageEntity> mImageList = Arrays.asList(
            // 2010-04-01
            new ImageEntity(1270087788000L, Color.DKGRAY),
            new ImageEntity(1270087788000L, Color.DKGRAY),

            // 2020-01-01
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),

            // 2020-02-20
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),

            // 2020-05-20
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),

            // 2020-08-20
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY)
    );

    private Timer mTimer;
    private AlbumAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new AlbumItemDecoration(recyclerView));
        mAdapter = new AlbumAdapter();
        recyclerView.setAdapter(mAdapter);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int index = new Random().nextInt(mImageList.size());
                        List<ImageEntity> list = new ArrayList<>();

                        for (int i = 0; i < index; i++) {
                            list.add(new ImageEntity(mImageList.get(i).getTimestamp(), mImageList.get(i).getImageColor()));
                        }

                        List<ImageEntity> data = mAdapter.getData();
                        data.addAll(list);

                        SpanSizeSortUtil.sort(3, data);

                        //adapter.getData().clear();
                        //adapter.getData().addAll(data);
                        mAdapter.notifyDataSetChanged();
                    }

                });
            }

        }, 0, 1000);
    }

    public void onStop(View view) {
        mTimer.cancel();

        List<ImageEntity> data = mAdapter.getData();
        for (ImageEntity datum : data) {
            Log.i(getClass().getSimpleName(), String.valueOf(datum.getSpanSize()));
        }
    }

}
