package id.ac.umn.projectuas_00000013536.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.umn.projectuas_00000013536.R;


public class TopAnimeFragment extends Fragment {

    // For List Of Anime View
    private RecyclerView recyclerView;
    private View v;

    public TopAnimeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_top_anime, container, false);

        // Setting Up RecyclerView
        recyclerView = v.findViewById(R.id.top_anime_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
