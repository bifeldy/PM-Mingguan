package id.ac.umn.week03_00000013536_v2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity implements FirstFragment.FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment firstFragment = new FirstFragment();
        fragmentTransaction.replace(R.id.third_activity_fragment_1, firstFragment);

        Fragment secondFragment = new SecondFragment();
        fragmentTransaction.replace(R.id.third_activity_fragment_2, secondFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onButtonClicked(String text) {
        SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager()
                                            .findFragmentById(R.id.third_activity_fragment_2);

        if(secondFragment != null) {
            secondFragment.updateTextView(text);
        }
        else {
            secondFragment = new SecondFragment();

            Bundle arguments = new Bundle();
            arguments.putString("tulisan", text);
            secondFragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.third_activity_fragment_2, secondFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}