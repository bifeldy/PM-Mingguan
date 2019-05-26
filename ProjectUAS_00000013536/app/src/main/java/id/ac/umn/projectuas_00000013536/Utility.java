package id.ac.umn.projectuas_00000013536;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class Utility {

    public static int calculateNumOfColumns(Context context, float columnWidthDp) {

        // For example column Width dp = 180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;

        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

    public static void keyboardListener(final LinearLayout rootView, final ImageView logo, final TextView title) {

        // Check If Keyboard Is Opened Or Not
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        // Rectangle
                        Rect r = new Rect();
                        rootView.getWindowVisibleDisplayFrame(r);
                        int screenHeight = rootView.getRootView().getHeight();

                        // r.bottom is the position above soft keypad or device button.
                        // If keypad is shown, the r.bottom is smaller than that before.
                        int keypadHeight = screenHeight - r.bottom;

                        // 0.15 ratio is perhaps enough to determine keypad height.
                        if (keypadHeight > screenHeight * 0.15) {

                            // Keyboard Opened
                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    Gravity.NO_GRAVITY

                            );
                            params.setMargins(0, 144, 0, 0);
                            rootView.setLayoutParams(params);

                            // Hide Logo & Title
                            logo.setVisibility(View.GONE);
                            title.setVisibility(View.GONE);
                        }
                        else {

                            // Keyboard Closed
                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    Gravity.CENTER
                            );
                            params.setMargins(0, 0, 0, 0);
                            rootView.setLayoutParams(params);

                            // Show Logo & Title
                            logo.setVisibility(View.VISIBLE);
                            title.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
    }

    private static boolean copyDatabase(Context context) {

        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DB_NAME);
            String outFileName = DatabaseHelper.DB_LOCATION + DatabaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;

            while((length = inputStream.read(buff)) > 0) {

                outputStream.write(buff, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            return true;
        }
        catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static void importDatabase(Context context, DatabaseHelper mDBHelper) {

        File database = context.getDatabasePath(DatabaseHelper.DB_NAME);
        if(!database.exists()) {
            mDBHelper.getReadableDatabase();

            // Copy DB
            if(!copyDatabase(context)) {
                Toast.makeText(context, "Gagal Membuka Database", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static String getSeasonMonth(int season) {

        String seasons[] = {
                "winter",
                "spring",
                "summer",
                "fall",
        };

        if(season < 0 || season >= seasons.length) {
            return seasons[Calendar.getInstance().get(Calendar.MONTH) / seasons.length];
        }

        return seasons[season];
    }
}