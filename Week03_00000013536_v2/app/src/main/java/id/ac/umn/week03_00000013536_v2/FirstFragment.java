package id.ac.umn.week03_00000013536_v2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FirstFragment extends Fragment {
    FragmentListener listener;
    EditText edtTulisan;
    Button btnBerubah;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        edtTulisan = view.findViewById(R.id.fragment_first_edittext_tulisan);
        btnBerubah = view.findViewById(R.id.fragment_first_button_berubah);

        btnBerubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked(edtTulisan.getText().toString());
            }
        });

        return view;
    }

    interface FragmentListener {
        void onButtonClicked(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentListener){
            listener = (FragmentListener) context;
        }
        else {
            throw new ClassCastException(context.toString());
        }
    }
}
