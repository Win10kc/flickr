package com.example.flickr.fragment;

import android.os.Bundle;
<<<<<<< HEAD:Flickr/app/src/main/java/com/example/flickr/fragment/MenuFragment.java
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flickr.R;

import java.util.List;
=======


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.flickr.R;

>>>>>>> origin/BI11-183:app/src/main/java/com/example/flickr/fragment/MenuFragment.java


public class MenuFragment extends Fragment {

<<<<<<< HEAD:Flickr/app/src/main/java/com/example/flickr/fragment/MenuFragment.java
    private RecyclerView recyclerView;

    private List<MenuItem> menuItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

=======
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment_search layout
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

>>>>>>> origin/BI11-183:app/src/main/java/com/example/flickr/fragment/MenuFragment.java

        return view;
    }
}

