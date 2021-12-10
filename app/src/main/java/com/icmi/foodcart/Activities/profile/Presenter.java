package com.icmi.foodcart.Activities.profile;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.icmi.foodcart.Model.Order;
import com.icmi.foodcart.Utils.Database;

import java.util.List;

public class Presenter implements Contractor.Presenter, OnSuccessListener<List<Order>>, OnFailureListener {
    private Contractor.View mView;
    private Database db;

    public Presenter(Contractor.View mView) {
        this.mView = mView;
        db = new Database();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getOrderHistory() {
        db.getOrderHistory(this, this);
    }

    @Override
    public void onSuccess(List<Order> orders) {
        mView.onResult(orders);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        mView.onError(e);
    }
}
