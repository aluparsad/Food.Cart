package com.icmi.foodcart.Application;

import android.app.Application;
import android.content.Intent;

import com.icmi.foodcart.Activities.Auth.AuthActivity;

public class application extends Application implements Contractor.View {

    private Contractor.Presenter presenter;
    private static Application instance;

    private void Init() {
        presenter = new Presenter(this);
        instance = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Init();
        presenter.InitializeConstants();
    }


    @Override
    public void Login() {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
