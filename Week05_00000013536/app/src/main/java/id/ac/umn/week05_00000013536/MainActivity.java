package id.ac.umn.week05_00000013536;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // String[] prePopulate = {
    //         "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
    //         "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    // };

    private List<Mahasiswa> mahasiswas;
    private MahasiswaAdapter mahasiswaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateData();

        /* ListView lvwMain = findViewById(R.id.main_list_mahasiswa); */

        // ArrayAdapter<String> adpMain = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, prePopulate);
        // lvwMain.setAdapter(adpMain);

        /// mahasiswaAdapter = new MahasiswaAdapter(this, R.layout.row_mahasiswa, mahasiswas);
        /// lvwMain.setAdapter(mahasiswaAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        MahasiswaRecyclerAdapter mahasiswaRecyclerAdapter = new MahasiswaRecyclerAdapter(this, R.layout.row_mahasiswa, mahasiswas);

        RecyclerView rvwMain = findViewById(R.id.main_list_mahasiswa);

        rvwMain.setLayoutManager(layoutManager);
        rvwMain.setAdapter(mahasiswaRecyclerAdapter);
    }

    private void populateData() {
        // TODO tambahkan data dummy mahasiswas disini
        mahasiswas = new ArrayList<>();
        mahasiswas.add(new Mahasiswa("13373", "Andri", "Setiady", "andri.setiady111@gmail.com"));
        mahasiswas.add(new Mahasiswa("13433", "Steven", "", "steveaja21@gmail.com"));
        mahasiswas.add(new Mahasiswa("13536", "B. Bias A.", "Christyono", "bifeldy@gmail.com"));
        mahasiswas.add(new Mahasiswa("13654", "Raynaldi", "Tjan", "raynalditjan@gmail.com"));
        mahasiswas.add(new Mahasiswa("14142", "Hans Aditia", "Lesmana", "hans.aditia@gmail.com"));
        mahasiswas.add(new Mahasiswa("14826", "Rizki Dwijaya", "Sakti", "rizki.sakti@gmail.com"));
    }
}
