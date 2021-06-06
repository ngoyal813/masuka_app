package com.example.masuka_app.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.masuka_app.R;
import com.example.masuka_app.adapter.country_adapter;
import com.example.masuka_app.api.country_api;
import com.example.masuka_app.api.retrofit_instance;
import com.example.masuka_app.dao.country_dao;
import com.example.masuka_app.models.country_model;
import com.example.masuka_app.repository.country_repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private country_adapter adapter;
    private List<country_model> country_modelList;
    private country_repository repository;
    private country_api country_api;
    private Button delete;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        repository = new country_repository(getActivity().getApplication());
        adapter = new country_adapter(getContext(),country_modelList);
        country_modelList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_home);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        network_request();
        delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.deletealldata();
            }
        });
        return view;
    }

    private void network_request() {
        country_api = retrofit_instance.getRetroClient().create(com.example.masuka_app.api.country_api.class);
        Call<List<country_model>> call = country_api.getcountries();
        call.enqueue(new Callback<List<country_model>>() {
            @Override
            public void onResponse(Call<List<country_model>> call, Response<List<country_model>> response) {
                    repository.insert(response.body());
            }

            @Override
            public void onFailure(Call<List<country_model>> call, Throwable t) {
                Log.d("api_error", t.toString());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getCountry_list().observe(getViewLifecycleOwner(), new Observer<List<country_model>>() {
            @Override
            public void onChanged(List<country_model> country_models) {
                country_modelList= country_models;
                adapter.setCountries_list(country_models);
                Log.d("main",""+country_modelList);
            }
        });
    }

}