package com.example.httprequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.newsAdapterViewHolder>{

    private List<newsitem> wallpaperItems = Constants.matchespics;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public newsAdapter(List<newsitem> wallpaperItems) {
        this.wallpaperItems = Constants.matchespics;
    }

    @NonNull
    @Override
    public newsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new newsAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull newsAdapterViewHolder holder, int position) {
        holder.setWallImage(Constants.matchespics.get(position));

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
        return Constants.matchespics.size();
    }

    class newsAdapterViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView wallImageView;
        public newsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            wallImageView = itemView.findViewById(R.id.wallpaperPost1);


        }
        void setWallImage(newsitem wallpaperItem){
            wallImageView.setImageResource(wallpaperItem.getImage1());

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
