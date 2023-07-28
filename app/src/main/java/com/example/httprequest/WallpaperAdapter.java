package com.example.httprequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>{

    private List<WallpaperItem> wallpaperItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public WallpaperAdapter(List<WallpaperItem> wallpaperItems) {
        this.wallpaperItems = wallpaperItems;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WallpaperViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        holder.setWallImage(wallpaperItems.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return wallpaperItems.size();
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView wallImageView;
        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            wallImageView = itemView.findViewById(R.id.wallpaperPost);


        }
        void setWallImage(WallpaperItem wallpaperItem){
            wallImageView.setImageResource(wallpaperItem.getImage());

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
