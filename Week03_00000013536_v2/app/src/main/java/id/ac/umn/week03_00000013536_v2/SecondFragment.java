package id.ac.umn.week03_00000013536_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    TextView txtTulisan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        txtTulisan = view.findViewById(R.id.fragment_second_textview_tulisan);
        if(getArguments() != null) {
            updateTextView(getArguments().getString("tulisan"));
        }

        return view;
    }

    public void updateTextView(String text){
        txtTulisan.setText(text);
    }
}