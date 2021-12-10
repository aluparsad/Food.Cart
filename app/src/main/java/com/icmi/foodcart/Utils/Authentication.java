package com.icmi.foodcart.Utils;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.icmi.foodcart.Model.User;


public class Authentication {

    public Authentication() {

    }

    public static void setUserConstants(FirebaseUser user) {
        User u = new User();
        u.setUserId(user.getUid());
        u.setNumber(user.getPhoneNumber());
        Constants.setUser(u);
    }


    public static void RegisterUser(FirebaseUser user) {
        DocumentReference dr = Constants.getUsersRef().document();

        User u = new User();
        u.setUserId(user.getUid());
        u.setNumber(user.getPhoneNumber());

        dr.set(u)
                .addOnSuccessListener(aVoid -> {
                    Constants.setUser(u);
                });
    }

    public static void logout(){
        FirebaseAuth.getInstance().signOut();
        Log.d("auth", "User: "+ FirebaseAuth.getInstance().getCurrentUser());
    }

}
