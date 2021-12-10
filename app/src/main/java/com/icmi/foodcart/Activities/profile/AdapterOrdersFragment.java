package com.icmi.foodcart.Activities.profile;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.icmi.foodcart.Model.Order;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Utils.Listener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterOrdersFragment extends RecyclerView.Adapter<AdapterOrdersFragment.ViewHolder> {
    private List<Order> data;
    private Listener.OnItemClickListener listener;

    public AdapterOrdersFragment() {
        data = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setOrders(@NonNull List<Order> orders) {
        data.addAll(0, orders);
        sort();
        notifyItemInserted(0);

    }

    public void setItemClickListener(Listener.OnItemClickListener listener) {
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sort() {
        data.sort((left, right) -> left.getTime().compareTo(right.getTime()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_history_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setOrderView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView orderId,
                name,
                status,
                quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.imageOrder);
            orderId = itemView.findViewById(R.id.orderIdTV);
            name = itemView.findViewById(R.id.foodnameTV);
            quantity = itemView.findViewById(R.id.quantityTV);
            status = itemView.findViewById(R.id.status);

            itemView.setOnClickListener(v -> listener.onClick(data.get(getAdapterPosition())));
        }

        public void setOrderView(Order order) {
            Picasso.get().load(order.getFood().getImage()).into(itemImage);
            orderId.setText(order.getOrderId());
            name.setText(order.getFood().getName());
            status.setText(order.getStatus().toString());
            quantity.setText("QTY : " + order.getQuantity());
        }
    }
}

