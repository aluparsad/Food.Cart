package com.icmi.foodcart.Activities.profile;

import com.icmi.foodcart.Model.Order;
import com.icmi.foodcart.Utils.Listener;

import java.util.List;

public interface Contractor {
    interface View extends Listener.OnResultListener<List<Order>> {
        void onError(Exception e);
    }

    interface Presenter {
        void getOrderHistory();
    }
}
