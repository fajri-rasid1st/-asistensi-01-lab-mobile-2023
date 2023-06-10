package com.example.assignment8;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private ProgressBar progressBar;
    private TextInputEditText textInputEditText;
    private RecyclerView rvContact;

    private AdapterSearch adapterSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        textInputEditText = view.findViewById(R.id.etSearch);
        rvContact = view.findViewById(R.id.rvContact);

        adapterSearch = new AdapterSearch();
        rvContact.setLayoutManager(new LinearLayoutManager(getContext()));
        rvContact.setAdapter(adapterSearch);

        // agar bisa melacak apa yg diketik di serch box
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    //method untuk melakukan pencarian
    private void search(CharSequence query){
        showProgressBar(true);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //ini untuk ngecek apakah edit text (text input et) kosong/tdk
            handler.post(() -> {
                if (TextUtils.isEmpty(query)){
                    adapterSearch.setPosts(new ArrayList<>());
                }else{
                    adapterSearch.setPosts(DataSource.getPostsByQuery(query.toString()));
                }

                adapterSearch.notifyDataSetChanged();

                showProgressBar(false);
            });
        } );
    }

    //untuk munculkan progress bar/hasil pencarian
    public void showProgressBar(boolean isShow){
        if (isShow){
            progressBar.setVisibility(View.VISIBLE);
            rvContact.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            rvContact.setVisibility(View.VISIBLE);
        }
    }
}







