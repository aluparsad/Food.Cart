package com.icmi.foodcart.Activities.profile;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.icmi.foodcart.Activities.Auth.AuthActivity;
import com.icmi.foodcart.Activities.FoodShowCase.Activity.MainActivity;
import com.icmi.foodcart.Model.Order;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Utils.Authentication;
import com.icmi.foodcart.Utils.Constants;
import com.icmi.foodcart.Utils.Listener;

import java.util.List;

public class ProfileActivity extends AppCompatActivity implements Listener.OnItemClickListener, Contractor.View {

    private TextView num;
    private ImageButton btnLogout;
    private Contractor.Presenter presenter;
    private RecyclerView rv;
    private AdapterOrdersFragment adapter;
    private LinearLayoutManager manager;
    private LottieAnimationView loadingView;


    private void Init() {
        num = findViewById(R.id.profileNumTv);
        btnLogout = findViewById(R.id.btnLogout);
        presenter = new Presenter(this);
        loadingView = findViewById(R.id.loadingView);

        adapter = new AdapterOrdersFragment();
        manager = new LinearLayoutManager(this);

        rv = findViewById(R.id.rv_orders);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        Init();
        num.setText(Constants.getUser().getNumber());

        btnLogout.setOnClickListener(v -> {
            Authentication.logout();
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        });

        adapter.setItemClickListener(this);
        showLoading();
        presenter.getOrderHistory();
    }

    private void showLoading() {
        loadingView.playAnimation();
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        loadingView.pauseAnimation();
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void onError(Exception e) {
        hideLoading();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResult(List<Order> result) {
        adapter.setOrders(result);
        hideLoading();
    }

    @Override
    public void onClick(Object item) {
        //todo: attach onCLick listener
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}