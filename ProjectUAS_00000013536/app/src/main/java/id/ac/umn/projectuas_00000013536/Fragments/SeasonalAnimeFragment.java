package id.ac.umn.projectuas_00000013536.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.ac.umn.projectuas_00000013536.APIs.SeasonAPI;
import id.ac.umn.projectuas_00000013536.R;
import id.ac.umn.projectuas_00000013536.Utility;

public class SeasonalAnimeFragment extends Fragment {

    // For List Of Anime View
    private RecyclerView recyclerView;
    private View v;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    // Anime
    private SeasonAPI seasonAPI;

    // UI Object
    private Spinner seasonDropDown, yearDropDown;
    private TextView seasonal_information, anime_search_text;
    private String seasonValue, yearValue;
    private Button anime_search_button;

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

        // Setting Up RecyclerView
        recyclerView = v.findViewById(R.id.seasonal_anime_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mNoOfColumns));

        // Find Object
        seasonal_information = v.findViewById(R.id.seasonal_information);

        anime_search_text = v.findViewById(R.id.anime_search_text);
        anime_search_button = v.findViewById(R.id.anime_search_button);
        anime_search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(seasonAPI.getSeasonAdapter() != null) {
                    seasonAPI.getSeasonAdapter().getFilter().filter(anime_search_text.getText().toString());
                }
            }
        });

        seasonDropDown = v.findViewById(R.id.season_type);

        List<String> seasonSpinner = new ArrayList<>();
        seasonSpinner.add("Winter (Jan-Mar)");
        seasonSpinner.add("Spring (Apr-Jun)");
        seasonSpinner.add("Summer (Jul-Sept)");
        seasonSpinner.add("Fall (Oct-Dec)");

        ArrayAdapter<String> seasonAdapter = new ArrayAdapter<String>(
                v.getContext(),
                R.layout.spinner_item_season,
                seasonSpinner
        );
        seasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        seasonDropDown.setAdapter(seasonAdapter);
        seasonDropDown.setSelection((Calendar.getInstance().get(Calendar.MONTH))/4);

        seasonDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)  seasonValue = "winter";
                else if(position == 1)  seasonValue = "spring";
                else if(position == 2)  seasonValue = "summer";
                else if(position == 3)  seasonValue = "fall";
                loadSeasonalAnime();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearDropDown = v.findViewById(R.id.season_year);

        List<String> yearSpinner = new ArrayList<>();
        for(int i=Calendar.getInstance().get(Calendar.YEAR); i>=1917; i--) {
            yearSpinner.add(Integer.toString(i));
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
                v.getContext(),
                R.layout.spinner_item_year,
                yearSpinner
        );
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        yearDropDown.setAdapter(yearAdapter);

        yearDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearValue = parent.getItemAtPosition(position).toString();
                loadSeasonalAnime();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadSeasonalAnime() {

        String loadingInfo = "Fetching Data From Server ...";
        seasonal_information.setVisibility(View.VISIBLE);
        seasonal_information.setText(loadingInfo);
        seasonal_information.setTextColor(Color.GREEN);

        // Seasonal Anime List API
        seasonAPI = new SeasonAPI(
                v.getContext(),
                yearValue == null ? Calendar.getInstance().get(Calendar.YEAR) : Integer.parseInt(yearValue),
                seasonValue == null ? Utility.getSeasonMonth(Calendar.getInstance().get(Calendar.YEAR)) : seasonValue,
                recyclerView,
                R.layout.seasonal_anime
        );
        seasonAPI.fetchJikanMAL(seasonal_information);
    }
}
