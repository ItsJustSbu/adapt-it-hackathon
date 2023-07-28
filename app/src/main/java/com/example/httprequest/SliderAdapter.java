package com.example.httprequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<items> sliderItems;

    public SliderAdapter(Context context, List<items> sliderItems) {
        this.context = context;
        this.sliderItems = sliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        items item = sliderItems.get(position);

        Glide.with(viewHolder.itemView)
                .load(item.getImageResource())
                .into(viewHolder.image);

        viewHolder.title.setText(item.getTitle());
        viewHolder.description.setText(item.getDescription());
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }

    class SliderAdapterVH extends ViewHolder {

        View itemView;
        ImageView image;
        TextView title;
        TextView description;

        SliderAdapterVH(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgslid);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            this.itemView = itemView;
        }
    }
}
