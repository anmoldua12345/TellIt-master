package com.example.makroid.tellit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentStoryFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    View view;
    public Fragment newInstance(String param1) {
        RecentStoryFragment fragment = new RecentStoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.strory_list,container,false);
        ButterKnife.bind(this,view);
        adapter = new RecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
