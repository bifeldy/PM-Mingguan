package id.ac.umn.projectuas_00000013536.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.umn.projectuas_00000013536.POJOs.Genre;
import id.ac.umn.projectuas_00000013536.POJOs.Producer;
import id.ac.umn.projectuas_00000013536.POJOs.Seasonal;
import id.ac.umn.projectuas_00000013536.POJOs.SeasonalAnime;
import id.ac.umn.projectuas_00000013536.R;
import id.ac.umn.projectuas_00000013536.SeasonAdapter;
import id.ac.umn.projectuas_00000013536.Utility;


public class SeasonalAnimeFragment extends Fragment {

    // For List Of Anime View
    private RecyclerView recyclerView;
    private View v;

    // Anime
    private List<Seasonal> seasonals;
    private List<SeasonalAnime> anime;
    private List<Genre> genres;
    private List<Producer> producers;
    private List<String> licensors;

    // Required empty public constructor
    public SeasonalAnimeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_seasonal_anime, container, false);

        // Determine Size Of Column To View As Grid
        int mNoOfColumns = Utility.calculateNumOfColumns(getContext(), 175);

        // Dummy Data
        populateData();

        // Setting Up RecyclerView
        recyclerView = v.findViewById(R.id.seasonal_anime_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mNoOfColumns));
        recyclerView.setAdapter(new SeasonAdapter(getContext(), R.layout.seasonal_anime, anime));

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    // Dummy Data
    private void populateData() {

        genres = new ArrayList<>();
        genres.add(
            new Genre(
                1,
                "anime",
                "Action",
                "https://myanimelist.net/anime/genre/1/Action"
            )
        );
        genres.add(
            new Genre(
                24,
                "anime",
                "Sci-Fi",
                "https://myanimelist.net/anime/genre/24/Sci-Fi"
            )
        );

        producers = new ArrayList<>();
        producers.add(
            new Producer(
                7,
                "anime",
                "J.C.Staff",
                "https://myanimelist.net/anime/producer/7/JCStaff"
            )
        );

        licensors = new ArrayList<>();
        licensors.add(
            new String(
                "Viz Media"
            )
        );

        anime = new ArrayList<>();
        anime.add(
            new SeasonalAnime(
                34134,
                "https://myanimelist.net/anime/34134/One_Punch_Man_2nd_Season",
                "One Punch Man 2nd Season",
                "https://cdn.myanimelist.net/images/anime/1805/99571.jpg?s=5e6b40e6a6ab30a5b2a9e616868a768d",
                "The second season of One Punch Man.",
                "TV",
                "2019-04-09T16:35:00+00:00",
                0,
                442956,
                genres,
                "Web manga",
                producers,
                7.98,
                licensors,
                false,
                false,
                false
            )
        );

        seasonals = new ArrayList<>();
        seasonals.add(
            new Seasonal(
                "request:season:73ea8b23744657356aa7431d179bade4c87a56e9",
                true,
                52347,
                "Spring",
                2019,
                anime
            )
        );
    }
}
