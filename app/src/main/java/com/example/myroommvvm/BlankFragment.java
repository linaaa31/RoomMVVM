package com.example.myroommvvm;

import android.os.Bundle;
import androidx.annotation.NonNull;import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;import android.view.View;
import android.view.ViewGroup;import android.widget.TextView;

public class BlankFragment extends Fragment {
    private  TextView addButton ;
    private TextView itemCount ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addButton = view.findViewById(R.id.add_item);
        itemCount = view.findViewById(R.id.item_count);
        FilmViewModel filmViewModel = new ViewModelProvider(getActivity()).get(FilmViewModel.class);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {
                filmViewModel.addFilm("Avengers", 220);
                filmViewModel.addCinema("Rubinyants st.");
        }        });
        filmViewModel.getfilms().observe(getActivity(), filmEntities -> {
           // Log.i("UWC", "Employees live data in fragment");
            itemCount.setText(String.valueOf(filmEntities.size()));
        });
        filmViewModel.getCinemas().observe(getActivity(), cinemaEntities -> {
           // Log.i("UWC", "Employees live data in fragment");
            itemCount.setText(String.valueOf(cinemaEntities.size()));
        });
    }}