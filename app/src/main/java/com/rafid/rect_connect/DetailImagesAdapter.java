package com.rafid.rect_connect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailImagesAdapter extends RecyclerView.Adapter<DetailImagesAdapter.DetailImageViewHolder> {

    private List<Integer> detailImages;
    private OnImageClickListener onImageClickListener;

    // Interface for handling image clicks
    public interface OnImageClickListener {
        void onImageClick(int imageResId);
    }

    // Constructor
    public DetailImagesAdapter(List<Integer> detailImages, OnImageClickListener onImageClickListener) {
        this.detailImages = detailImages;
        this.onImageClickListener = onImageClickListener;
    }

    @NonNull
    @Override
    public DetailImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_image_item, parent, false);
        return new DetailImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailImageViewHolder holder, int position) {
        int imageResource = detailImages.get(position);
        holder.detailImageView.setImageResource(imageResource);

        // Set click listener for each image
        holder.detailImageView.setOnClickListener(v -> {
            if (onImageClickListener != null) {
                onImageClickListener.onImageClick(imageResource);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailImages != null ? detailImages.size() : 0;
    }

    public static class DetailImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView detailImageView;

        public DetailImageViewHolder(@NonNull View itemView) {
            super(itemView);
            detailImageView = itemView.findViewById(R.id.detail_image);
        }
    }
}
