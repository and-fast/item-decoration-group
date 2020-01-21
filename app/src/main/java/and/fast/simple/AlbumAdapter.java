package and.fast.simple;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import and.fast.widget.itemdecorationgroup.model.TimestampProvider;
import and.fast.simple.entities.ImageEntity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> implements TimestampProvider {

    private List<ImageEntity> mImageList = Arrays.asList(
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),
            new ImageEntity(1577808000000L, Color.BLACK),

            // ...
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),
            new ImageEntity(1582188504000L, Color.DKGRAY),

            // ...
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),
            new ImageEntity(1589964504000L, Color.GRAY),

            // ...
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY),
            new ImageEntity(1597913304000L, Color.LTGRAY)
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


    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvAlbum;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvAlbum = itemView.findViewById(R.id.iv_album);
        }
    }

}
