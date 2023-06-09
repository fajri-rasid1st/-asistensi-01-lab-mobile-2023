package com.example.backgroundthreadfragment;

import static com.example.backgroundthreadfragment.HomeFragment.dataList;

import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    SearchView searchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }
        });
    }
    private void performSearch(String query) {
        // Buat ArrayList baru untuk menampung hasil pencarian
        ArrayList<ModelDummy> searchResults = new ArrayList<>();

        // Lakukan pencarian dengan membandingkan query dengan dataDummy
        for (ModelDummy modelDummy : DataDummy.dataDummy) {
            if (modelDummy.getNickname().toLowerCase().contains(query.toLowerCase()) ||
                    modelDummy.getUsername().toLowerCase().contains(query.toLowerCase())) {
                // Jika data cocok dengan query, tambahkan ke searchResults
                searchResults.add(modelDummy);
            }
        }

        // Tampilkan hasil pencarian
        showSearchResults(searchResults);
    }

    // Metode untuk menampilkan hasil pencarian
    private void showSearchResults(ArrayList<ModelDummy> searchResults) {
        // TODO: Implementasikan tampilan hasil pencarian (misalnya menggunakan RecyclerView)
        // Disini Anda dapat mengupdate tampilan RecyclerView atau komponen lain sesuai dengan hasil pencarian yang ditemukan
        // Contoh: recyclerView.setAdapter(new MyAdapter(searchResults));
        // Anda juga bisa menampilkan pesan jika hasil pencarian kosong
        if (searchResults.isEmpty()) {
            Toast.makeText(getContext(), "No Result Found", Toast.LENGTH_SHORT).show();
        }
    }

}