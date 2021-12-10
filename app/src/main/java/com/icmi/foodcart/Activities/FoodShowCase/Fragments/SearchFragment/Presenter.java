package com.icmi.foodcart.Activities.FoodShowCase.Fragments.SearchFragment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.icmi.foodcart.Utils.Database;

public class Presenter implements Contractor.Presenter {

    private Contractor.View mView;
    private Database db;

    public Presenter(Contractor.View mView) {
        this.mView = mView;
        db = new Database();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void QueryResult(String query) {
        db.getQueryResult(query, result -> {
            mView.onResult(result);
        });
    }

}
