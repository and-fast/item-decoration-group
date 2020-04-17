package and.fast.simple.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import and.fast.simple.R;
import and.fast.simple.data.entities.ImageEntity;
import and.fast.widget.itemdecorationgroup.model.SpanSizeModel;
import and.fast.widget.itemdecorationgroup.model.ModelProvider;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> implements ModelProvider {

    private List<ImageEntity> mImageList = new ArrayList<>();

    public List<ImageEntity> getData() {
        return mImageList;
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, final int position) {
        final ImageEntity item = mImageList.get(position);
        //holder.mIvAlbum.setBackgroundColor(item.getImageColor());

        Glide.with(holder.mIvAlbum).load(item.getUrl()).into(holder.mIvAlbum);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holder.mTvDate.setText(sdf.format(item.getTimestamp()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                Log.i(getClass().getSimpleName(), item.getSpanSize() + ", " + position);
            }

        });
    }

    public void setNewData(List<ImageEntity> data){
        mImageList.clear();
        mImageList = data;
        notifyDataSetChanged();
    }

    public void addData(List<ImageEntity> data){
        mImageList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    @Override
    public List<? extends SpanSizeModel> getModels() {
        return mImageList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvAlbum;
        private TextView  mTvDate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvAlbum = itemView.findViewById(R.id.iv_album);
            mTvDate = itemView.findViewById(R.id.tv_date);
        }
    }

}
