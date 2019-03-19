package id.ac.umn.week06_00000013536;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

public class Setting2Activity extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new Setting2Fragment())
                .commit();

        // Soal Terakhir Cuman Tambahin Ini Doank ?? Serius ??
        setResult(RESULT_OK);
    }

    public static class Setting2Fragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_settings);
        }
    }
}
