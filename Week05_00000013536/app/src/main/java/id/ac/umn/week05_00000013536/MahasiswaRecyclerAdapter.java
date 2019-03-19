package id.ac.umn.week05_00000013536;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MahasiswaRecyclerAdapter extends RecyclerView.Adapter<MahasiswaRecyclerAdapter.MahasiswaRecyclerViewHolder> {

    private List<Mahasiswa> mahasiswas;
    private Context recyclerContext;
    private int rowLayout;

    public MahasiswaRecyclerAdapter(Context recyclerContext, int rowLayout, List<Mahasiswa> mahasiswas) {
        this.recyclerContext = recyclerContext;
        this.rowLayout = rowLayout;
        this.mahasiswas = mahasiswas;
    }

    @NonNull
    @Override
    public MahasiswaRecyclerAdapter.MahasiswaRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.recyclerContext);
        View view = inflater.inflate(rowLayout, viewGroup, false);

        return new MahasiswaRecyclerAdapter.MahasiswaRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaRecyclerAdapter.MahasiswaRecyclerViewHolder mahasiswaRecyclerViewHolder, int i) {
        Mahasiswa mahasiswa = mahasiswas.get(i);

        mahasiswaRecyclerViewHolder.textNIM.setText(mahasiswa.getNIM());
        mahasiswaRecyclerViewHolder.textEmail.setText(mahasiswa.getEmail());
    }

    @Override
    public int getItemCount() {
        return (mahasiswas != null) ? mahasiswas.size() : 0;
    }

    public class MahasiswaRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView textNIM, textEmail;

        public MahasiswaRecyclerViewHolder(@NonNull final View itemView) {
            super(itemView);

            textNIM = itemView.findViewById(R.id.row_mahasiswa_text_nim);
            textEmail = itemView.findViewById(R.id.row_mahasiswa_text_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Toast.makeText(itemView.getContext(), mahasiswas.get(position).getFirstName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
