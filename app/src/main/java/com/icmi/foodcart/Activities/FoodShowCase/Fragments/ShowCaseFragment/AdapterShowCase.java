package com.icmi.foodcart.Activities.FoodShowCase.Fragments.ShowCaseFragment;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icmi.foodcart.Model.Category;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Utils.Listener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterShowCase extends RecyclerView.Adapter<AdapterShowCase.ViewHolder> {

    private List<Category> data;
    private Listener.OnItemClickListener listener;


    public AdapterShowCase(List<Category> data) {
        this.data = new ArrayList<>();
        if (data != null)
            this.data.addAll(data);
    }

    public void setCategories(@NonNull List<Category> categories) {
        if (categories.size() > 0) {
            data = categories;
            notifyDataSetChanged();
        }
    }


    public void setItemClickListener(Listener.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_showcase, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private ImageView imagesGallery;

        private void setView(Category category) {
            categoryName.setText(category.getName());

            Picasso.get()
                    .load(Uri.parse(category.getImage()))
                    .fit()
                    .into(imagesGallery);

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            imagesGallery = itemView.findViewById(R.id.categoryImage);

            itemView.setOnClickListener(v -> {
                if (listener != null)
                    listener.onClick(data.get(getAdapterPosition()));
            });
        }
    }

}

