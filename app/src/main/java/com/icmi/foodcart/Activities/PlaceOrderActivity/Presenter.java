package com.icmi.foodcart.Activities.PlaceOrderActivity;

import com.icmi.foodcart.Model.Order;
import com.icmi.foodcart.Utils.Database;


public class Presenter implements Contractor.Presenter {

    private Contractor.View mView;
    private Database db;

    public Presenter(Contractor.View mView) {
        this.mView = mView;
        db = new Database();
    }


    @Override
    public void getFood(String foodId) {
        db.getProduct(foodId, result -> {
            mView.setFoodItem(result);
        });
    }


    @Override
    public void placeOrder(Order order) {
        order.setTime(String.valueOf(java.lang.System.currentTimeMillis()));
        db.placeOrder(order, aVoid -> mView.OnSuccess());
    }
}