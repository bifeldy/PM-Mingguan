package id.ac.umn.week13_13536;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        TextView tvUid = findViewById(R.id.signedin_text_uid);
        TextView tvName = findViewById(R.id.signedin_text_user);
        tvUid.setText(currentUser.getUid());
        tvName.setText(currentUser.getDisplayName());

        Button btnSignout = findViewById(R.id.signedin_button_signout);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                    .signOut(SignedInActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                startActivity(
                                        new Intent( SignedInActivity.this, MainActivity.class));
                                finish();
                            }
                            else {
                                // Apabila error
                            }
                        }
                    });
            }
        });

        Button btnDelete = findViewById(R.id.signedin_button_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .delete( SignedInActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            startActivity(
                                new Intent( SignedInActivity.this, MainActivity.class)
                            );
                            finish();
                        }
                        else {
                            // Apabila error ...
                        }
                    }
                }) ;
            }
        }) ;
    }
}
