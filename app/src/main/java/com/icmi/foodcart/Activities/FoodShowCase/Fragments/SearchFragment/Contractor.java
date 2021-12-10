package com.icmi.foodcart.Activities.FoodShowCase.Fragments.SearchFragment;

import com.icmi.foodcart.Model.Food;
import com.icmi.foodcart.Utils.Listener;

import java.util.List;

public interface Contractor {
    interface View extends Listener.OnResultListener<List<Food>> {
    }

    interface Presenter {
        void QueryResult(String query);
    }
}
