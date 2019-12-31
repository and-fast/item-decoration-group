package and.fast.simple;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import and.fast.itemdecorationgroup.model.TimestampProvider;
import and.fast.simple.entities.ImageEntity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> implements TimestampProvider {

    private List<ImageEntity> mImageList = Arrays.asList(
            new ImageEntity(1577772269000L,Color.GRAY),   // 2019-12-31
            new ImageEntity(1575093869000L,Color.LTGRAY), // 2019-11-31
            new ImageEntity(1575093869000L,Color.CYAN),
            new ImageEntity(1575093869000L,Color.MAGENTA),
            new ImageEntity(1575093869000L,Color.DKGRAY),
            new ImageEntity(1569823469000L,Color.BLACK), // 2019-09-31
            new ImageEntity(1535609069000L,Color.DKGRAY), // 2018-8-30
            new ImageEntity(1535609069000L,Color.BLACK),
            new ImageEntity(1535609069000L,Color.LTGRAY),
            new ImageEntity(1535609069000L,Color.YELLOW),
            new ImageEntity(1535609069000L,Color.BLACK),
            new ImageEntity(1535609069000L,Color.CYAN),
            new ImageEntity(1535609069000L,Color.DKGRAY),
            new ImageEntity(1535609069000L,Color.MAGENTA),
            new ImageEntity(1514613869000L,Color.BLACK),  // 2017-12-30
            new ImageEntity(1514613869000L,Color.DKGRAY)
    );

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, final int position) {
        holder.mIvAlbum.setBackgroundColor(mImageList.get(position).getImageColor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    @Override
    public long getTimestamp(int position) {
        return mImageList.get(position).getTimestamp();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mIvAlbum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvAlbum = itemView.findViewById(R.id.iv_album);
        }
    }

}
