package com.icmi.foodcart.Activities.PlaceOrderActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.icmi.foodcart.Activities.FoodShowCase.Activity.MainActivity;
import com.icmi.foodcart.Model.Food;
import com.icmi.foodcart.Model.Order;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Utils.Constants;
import com.icmi.foodcart.Model.Status;
import com.squareup.picasso.Picasso;

public class PlaceOrderActivity extends AppCompatActivity implements Contractor.View {

    private Contractor.Presenter presenter;
    private String itemId;
    private Order order;
    private LottieAnimationView lottieFood, lottieLoading;
    private ImageView image_item;
    private Button placeOrder;
    private EditText
            quantity,
            locality,
            pincode,
            city;
    private TextView food_name, food_price;

    private void Init() {
        order = new Order();
        presenter = new Presenter(this);
        itemId = getIntent().getStringExtra("itemId");
        lottieLoading = findViewById(R.id.loadingView);
        lottieFood  = findViewById(R.id.food_anim);

        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        image_item = findViewById(R.id.food_item_image);
        placeOrder = findViewById(R.id.btnPlaceOrder);
        quantity = findViewById(R.id.quantityET);
        locality = findViewById(R.id.ET_locality);
        city = findViewById(R.id.ET_city);
        pincode = findViewById(R.id.ET_pincode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        getSupportActionBar().setTitle("Place Order");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        Init();
        ShowLoading();
        presenter.getFood(itemId);
    }


    @Override
    public void setFoodItem(Food food) {
        order.setFood(food);
        setPlaceOrderBtn();
        Picasso.get().load(food.getImage()).into(image_item);
        food_name.setText(food.getName());
        food_price.setText("Rs. " + food.getPrice());
        lottieFood.playAnimation();
        HideLoading();
    }

    private void setPlaceOrderBtn() {
        Food food = order.getFood();
        int price = food.getPrice();

        placeOrder.setOnClickListener(v -> {
            int quantty = Integer.decode(quantity.getText().toString());
            String address = locality.getText().toString() + " : " + city.getText().toString() + " : " + pincode.getText().toString();

            if (quantty > 0 && address.length() > 0) {
                ShowLoading();
                order.setQuantity(quantty);
                order.setPrice(price);
                order.setAddress(address);
                order.setClientId(Constants.getUser().getUserId());
                RequestPayment();
            }
        });
    }

    public void RequestPayment() {
        order.setStatus(Status.PROCESSING);
        presenter.placeOrder(order);
        ShowLoading();
    }


    @Override
    public void OnSuccess() {
        HideLoading();
        Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void ShowLoading() {
        lottieLoading.setVisibility(View.VISIBLE);
        lottieLoading.playAnimation();
    }

    private void HideLoading() {
        lottieLoading.pauseAnimation();
        lottieLoading.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}