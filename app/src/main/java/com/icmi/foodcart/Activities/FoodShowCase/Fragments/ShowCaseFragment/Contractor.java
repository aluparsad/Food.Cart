package com.icmi.foodcart.Activities.FoodShowCase.Fragments.ShowCaseFragment;

import com.icmi.foodcart.Model.Category;
import com.icmi.foodcart.Utils.Listener;

import java.util.List;

public interface Contractor {
    interface View extends Listener.OnResultListener<List<Category>> {
    }

    interface Presenter {
        void getCategories();
    }
}
