package com.icmi.foodcart.Activities.FoodShowCase.Fragments.ShowCaseFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.icmi.foodcart.Model.Category;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Utils.Listener;

import java.util.List;

public class ShowCaseFragment extends Fragment implements Contractor.View {

    private Contractor.Presenter presenter;
    private RecyclerView rv;
    private AdapterShowCase adapter;
    private RecyclerView.LayoutManager layoutManager;
    private final Listener.OnItemClickListener listener;
    private LottieAnimationView lam;

    public ShowCaseFragment(Listener.OnItemClickListener listener) {
        this.listener = listener;
    }

    private void Init(View v) {
        presenter = new Presenter(this);
        rv = v.findViewById(R.id.rv_showcase);
        lam = v.findViewById(R.id.loadingView);

        adapter = new AdapterShowCase(null);
        adapter.setItemClickListener(listener);
        layoutManager = new GridLayoutManager(getContext(), 2);

        rv.setAdapter(adapter);
        rv.setLayoutManager(layoutManager);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_showcase, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Init(v);
        ShowLoading();
        presenter.getCategories();
    }

    @Override
    public void onResult(@NonNull List<Category> result) {
        Log.d("adptr", "onResult: ");
        adapter.setCategories(result);
        HideLoading();
    }


    private void ShowLoading() {
        lam.setVisibility(View.VISIBLE);
        lam.playAnimation();
    }

    private void HideLoading() {
        lam.pauseAnimation();
        lam.setVisibility(View.GONE);
    }


}
