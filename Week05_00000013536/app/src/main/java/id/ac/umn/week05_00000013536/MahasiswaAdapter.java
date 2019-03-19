package id.ac.umn.week05_00000013536;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MahasiswaAdapter extends ArrayAdapter {
    private int rowLayout;
    private Context adapterContext;

    public MahasiswaAdapter(@NonNull Context context, int resource, @NonNull List<Mahasiswa> objects) {
        super(context, resource, objects);
        this.rowLayout = resource;
        this.adapterContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(adapterContext);
            v = inflater.inflate(rowLayout, null);
        }

        TextView textNIM = v.findViewById(R.id.row_mahasiswa_text_nim);
        TextView textEmail = v.findViewById(R.id.row_mahasiswa_text_email);

        Mahasiswa mahasiswa = (Mahasiswa) getItem(position);

        if(mahasiswa != null) {
            if(textNIM != null) {
                textNIM.setText(mahasiswa.getNIM());
            }
            if(textEmail != null) {
                textEmail.setText(mahasiswa.getEmail());
            }
        }

        return v;
    }
}