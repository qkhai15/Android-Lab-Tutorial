package com.example.recyclerviewexample.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> products) {
        this.productList = products;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, descTextView, ratingTextView, priceTextView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.descTextView.setText(product.getDescription());
        holder.ratingTextView.setText("Rating: " + product.getRating());
        holder.priceTextView.setText(String.format("%.1f", product.getPrice()));

        // Load ảnh từ URL bằng Picasso
        Picasso.get()
                .load(product.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground) // ảnh tạm
                .error(R.drawable.ic_launcher_foreground)       // ảnh khi lỗi
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
