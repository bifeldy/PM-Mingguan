package id.ac.umn.week13_13536;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private static final int REQUEST_CODE = 101;

    private List<AuthUI.IdpConfig> getProviderList() {
        List<AuthUI.IdpConfig> providers = new ArrayList<>();

        providers.add(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        return providers;
    }

    private void authenticateUser() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(getProviderList())
                .setIsSmartLockEnabled(false)
                .build(),
                REQUEST_CODE
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(this, SignedInActivity.class));
        }
        else {
            authenticateUser();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if(requestCode == REQUEST_CODE) {

            if(resultCode == RESULT_OK) {
                startActivity(new Intent(this, SignedInActivity.class));
                return;
            }
        }
        else {
            if(response == null) {

                // User tidak SignIn
                return;
            }

            if(response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {

                // Tidak ada Network
                return;
            }

            if(response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {

                // Unknown Error
                return;
            }
        }
    }
}
