package id.ac.umn.week13_b_13536;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mengambil instance class FirebaseDatabase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Mengambil reference dari database yang akan digunakan
        // By default, ada pada root treenxa (/)
        DatabaseReference dbRef = database.getReference();

        // Menimpa reference pada path yang lainnxa
        dbRef = database.getReference( "/coba/data/key01");
        dbRef.setValue("Halo data !");

        // Menimpa reference pada path yang lainnya
        dbRef = database.getReference("/coba/data/key01");
        dbRef.setValue("Halo data !");

        POJOUser user = new POJOUser(
                15000,
                "Lucida Console",
                new POJOEmail("lucidaconsole@gmail.com", true)
        );

        DatabaseReference.CompletionListener completionListener = new
            DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError,
                                       @NonNull DatabaseReference databaseReference) {
                    if(databaseError != null) {
                        //Handle apabila ada error
                    }
                }
            };

        dbRef = database.getReference("/profile2") ;
        dbRef.setValue(user, completionListener);

        ValueEventListener changeListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    long jumlahAnak = dataSnapshot.getChildrenCount();

                    for(DataSnapshot child : dataSnapshot.getChildren()) {
                        Log.i("DATA", "KEY: " + child.getKey());
                    }
                    //¥asukkan ke class POJOUser
                    POJOUser objUser = dataSnapshot.getValue(POJOUser.class);
                    Log.i("OBJECT","USERID: " + objUser.getUserid());
                    Log.i("OEJECT","USERNAME: " + objUser.getUsername());
                    Log.i("OBJECT","EMAIL: " + objUser.getEmail().getAddress());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        dbRef = database.getReference("/profile");
        dbRef.addValueEventListener(changeListener);

        /*
            Soal No. 21

            05-10 22:01:34.741 7777-7777/id.ac.umn.week13_b_13536 I/OBJECT: USERID: 13536
            05-10 22:01:34.741 7777-7777/id.ac.umn.week13_b_13536 I/OBJECT: EMAIL: 13536@gmail.com
         */
    }
}
