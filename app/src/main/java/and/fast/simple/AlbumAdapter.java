package and.fast.simple;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import and.fast.itemdecorationgroup.model.Timeline;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> implements Timeline {

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        holder.mIvAlbum.setBackgroundColor(Color.GRAY);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public long getTimestamp(int position) {
        if (position <= 5){
            return 1577238277L;

        } else if (position <= 15){
            return 1577411078L;
        }

        return 1577497477L;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mIvAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvAlbum = itemView.findViewById(R.id.iv_album);
        }
    }

}
