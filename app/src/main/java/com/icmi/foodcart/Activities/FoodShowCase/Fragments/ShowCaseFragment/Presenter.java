package com.icmi.foodcart.Activities.FoodShowCase.Fragments.ShowCaseFragment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.icmi.foodcart.Utils.Database;

public class Presenter implements Contractor.Presenter {

    private Contractor.View mView;

    public Presenter(Contractor.View mView) {
        this.mView = mView;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getCategories() {
        Database db = new Database();

        db.getCategories(result -> {
            mView.onResult(result);
        });
    }

}
