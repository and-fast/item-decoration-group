package and.fast.simple;

import android.os.Bundle;

import and.fast.itemdecorationgroup.AlbumItemDecoration;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new AlbumItemDecoration(recyclerView));
        recyclerView.setAdapter(new AlbumAdapter());
    }

}