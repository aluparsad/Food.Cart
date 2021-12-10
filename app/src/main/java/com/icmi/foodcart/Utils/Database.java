package com.icmi.foodcart.Utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.icmi.foodcart.Model.Category;
import com.icmi.foodcart.Model.Food;
import com.icmi.foodcart.Model.Order;

public class Database {


    public Database() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getQueryResult(String query, Listener.OnResultListener<List<Food>> listener) {
        List<Food> foods = new ArrayList<>();
        Constants
                .getProductsRef()
                .whereEqualTo("category", query) //Search.search1(query);
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    queryDocumentSnapshots.getDocuments()
                            .forEach(documentSnapshot -> {
                                foods.add(documentSnapshot.toObject(Food.class));
                            });

                    listener.onResult(foods);
                });
    }


    public void getProduct(String itemId, Listener.OnResultListener<Food> foodOnResultListener) {
        Log.d("db", "getProduct: " + itemId);
        Constants.getProductsRef()
                .document(itemId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Food f = documentSnapshot.toObject(Food.class);
                    foodOnResultListener.onResult(f);
                })
                .addOnFailureListener(e -> {
                    Log.e("db", "getProduct: ", e);
                });
    }

    public void getProducts(String category, Listener.OnResultListener<List<Food>> listOnResultListener) {
        Constants.getProductsRef()
                .whereEqualTo("category", category)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Food> foods = new ArrayList<>();
                    for (DocumentSnapshot ds : queryDocumentSnapshots) {
                        Food f = ds.toObject(Food.class);
                        foods.add(f);
                    }
                    listOnResultListener.onResult(foods);
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getCategories(Listener.OnResultListener<List<Category>> onResultListener) {
        Constants.getCategoriesRef()
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Category> catogs = new ArrayList<>();
                    
                    queryDocumentSnapshots.forEach(queryDocumentSnapshot -> catogs.add(queryDocumentSnapshot.toObject(Category.class)));

                    onResultListener.onResult(catogs);
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getOrderHistory(OnSuccessListener<List<Order>> successListener, OnFailureListener failureListener) {
        Constants.getOrdersRef()
                .whereEqualTo("clientId", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Order> ordersData = new ArrayList<>();
                    queryDocumentSnapshots.forEach(queryDocumentSnapshot -> {
                        ordersData.add(queryDocumentSnapshot.toObject(Order.class));
                    });
                    successListener.onSuccess(ordersData);
                })
                .addOnFailureListener(failureListener);
    }

    public void placeOrder(Order order, OnSuccessListener<Void> listener) {
        DocumentReference dr = Constants.getOrdersRef().document();
        order.setOrderId(dr.getId());
        dr.set(order).addOnSuccessListener(listener);
    }




}
