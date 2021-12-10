package com.icmi.foodcart.Activities.FoodShowCase.Fragments.SearchFragment;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icmi.foodcart.Model.Food;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Utils.Listener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolder> {

    private List<Food> foods;
    private Listener.OnItemClickListener listener;

    public AdapterSearch(List<Food> foods) {
        this.foods = new ArrayList<>();
        if (foods != null) {
            this.foods.addAll(foods);
        }
    }

    public void addFood(List<Food> foodItems) {
        if (foodItems != null) {
            foods.addAll(0,foodItems);
            notifyItemInserted(0);
        }
    }


    public void setItemClickListener(Listener.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItemView(foods.get(position));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView foodName, foodPrice;
        private ImageView foodImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.food_name);
            foodPrice = itemView.findViewById(R.id.food_price);
            foodImage = itemView.findViewById(R.id.food_item_image);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onClick(foods.get(getAdapterPosition()));
                }
            });
        }


        public void setItemView(Food food) {

            foodName.setText(food.getName());
            foodPrice.setText("Rs. " + food.getPrice());

            Picasso.get().load(Uri.parse(food.getImage()))
                    .fit()
                    .into(foodImage);
        }
    }
}
