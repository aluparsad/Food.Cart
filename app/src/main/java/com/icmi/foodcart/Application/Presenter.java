package com.icmi.foodcart.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.icmi.foodcart.Utils.Authentication;

public class Presenter implements Contractor.Presenter {

    private Contractor.View mView;

    public Presenter(Contractor.View mView) {
        this.mView = mView;
    }


    @Override
    public void InitializeConstants() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Authentication.setUserConstants(user);
        } else {
            mView.Login();
        }
    }

}
