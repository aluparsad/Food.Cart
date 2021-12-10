package com.icmi.foodcart.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.icmi.foodcart.Model.User;

public class Constants {

    private static User user;

    private static FirebaseFirestore
            instance = FirebaseFirestore.getInstance();

    public static final String
            Category = "CATEGORY",
            Categories = "CATEGORIES",
            Product = "PRODUCT",
            Users = "USERS";

    public static final String
            orderChannelId = "ORDERS";

    public static User getUser() {
        return user;
    }

    public static void setUser(User u) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            user = u;
        }
    }

    public static CollectionReference getUsersRef() {
        return instance.collection(Constants.Users);
    }

    public static CollectionReference getProductsRef() {
        return instance.collection(Constants.Product);
    }

    public static CollectionReference getCategoriesRef() {
        return instance.collection(Categories);
    }

    public static CollectionReference getOrdersRef() {
        return instance.collection("ORDERS");
    }

}
