package com.example.nafees.matcode;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HardFragment extends Fragment {

    ArrayList<Problem> problems;
    ListView listView;
    ProblemsAdapter adapter;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();

        View v = inflater.inflate(R.layout.hard_tab, container,false);

        problems = new ArrayList<>();
        listView = v.findViewById(R.id.listview);

        adapter = new ProblemsAdapter(getActivity(),problems);
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {



        super.onViewCreated(view, savedInstanceState);
    }
}
