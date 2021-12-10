package com.icmi.foodcart.Activities.PlaceOrderActivity;

import com.icmi.foodcart.Model.Food;
import com.icmi.foodcart.Model.Order;

public interface Contractor {
    interface View {
        void OnSuccess();
        void setFoodItem(Food food);
    }

    interface Presenter {
        void getFood(String foodId);
        void placeOrder(Order order);
    }
}

