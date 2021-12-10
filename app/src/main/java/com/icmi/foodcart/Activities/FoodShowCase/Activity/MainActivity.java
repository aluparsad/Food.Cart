package com.icmi.foodcart.Activities.FoodShowCase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.icmi.foodcart.Activities.Auth.AuthActivity;
import com.icmi.foodcart.Activities.PlaceOrderActivity.PlaceOrderActivity;
import com.icmi.foodcart.Activities.profile.ProfileActivity;
import com.icmi.foodcart.Model.Category;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Activities.FoodShowCase.Fragments.SearchFragment.SearchResultFragment;
import com.icmi.foodcart.Activities.FoodShowCase.Fragments.ShowCaseFragment.ShowCaseFragment;
import com.icmi.foodcart.Model.Food;
import com.icmi.foodcart.Utils.Constants;
import com.icmi.foodcart.Utils.Listener;

public class MainActivity extends AppCompatActivity implements Listener.OnItemClickListener {

    private Listener.OnItemClickListener listener;
    private Fragment currentFragment;
    private TextView title;

    private void InitializeView(@NonNull ActionBar actionBar) {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_action_bar);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        title = actionBar.getCustomView().findViewById(R.id.title_action);
        listener = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null)
            InitializeView(getSupportActionBar());

        title.setText("Menu");
        addFragment(new ShowCaseFragment(listener));
    }

    private void addFragment(Fragment fragment) {
        currentFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl, fragment)
                .commit();
    }

    @Override
    public void onClick(Object item) {
        if (item instanceof Category) {
            Category c = (Category) item;
            Log.d("test", "onClick: " + c.getName());
            title.setText("Food");
            addFragment(new SearchResultFragment(c.getName(), listener));
        }
        if (item instanceof Food) {
            Food f = (Food) item;
            Intent i = new Intent(this, PlaceOrderActivity.class);
            i.putExtra("itemId", f.getItemId());
            startActivity(i);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.profileBtn) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (currentFragment instanceof SearchResultFragment) {
            title.setText("Menu");
            addFragment(new ShowCaseFragment(this));
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Constants.getUser() == null || FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }
    }
}