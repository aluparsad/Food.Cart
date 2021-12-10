package com.icmi.foodcart.Activities.FoodShowCase.Fragments.SearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.icmi.foodcart.Model.Food;
import com.icmi.foodcart.R;
import com.icmi.foodcart.Utils.Listener;

import java.util.List;

public class SearchResultFragment extends Fragment implements Contractor.View {

    private String query;
    private Contractor.Presenter presenter;
    private RecyclerView rv;
    private LinearLayoutManager manager;
    private AdapterSearch adapter;
    private Listener.OnItemClickListener listener;
    private LottieAnimationView lam;

    public SearchResultFragment(String queryText, Listener.OnItemClickListener listener) {
        this.listener = listener;
        query = queryText;
    }


    private void Init(View v) {
        presenter = new Presenter(this);
        lam = v.findViewById(R.id.loadingView);

        rv = v.findViewById(R.id.rv_searchResult);
        manager = new LinearLayoutManager(v.getContext());
        adapter = new AdapterSearch(null);

        adapter.setItemClickListener(listener);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        Init(v);
        ShowLoading();
        presenter.QueryResult(query);

    }

    @Override
    public void onResult(List<Food> result) {
        adapter.addFood(result);
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
