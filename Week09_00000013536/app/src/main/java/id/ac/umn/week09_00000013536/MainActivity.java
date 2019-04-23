package id.ac.umn.week09_00000013536;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtTulisan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTulisan = findViewById(R.id.main_textview_tulisan);
    }

    // public void mainButtonThreadClicked(View view){
    //     int i = 0;
    //     while(i <= 20) {
    //         try {
    //             Thread.sleep(1000);
    //             i++;
    //         }
    //         catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //
    //     txtTulisan.setText("Setelah 20 detik kemudian");
    // }

    /*
    *   Nomor 5:
    *   Thread.sleep(); disini mengacu ke pada aktivitas intent yang sedang terbuka
    *   dimana pada saat tombol di tekan akan menjalankan perintah tersebut yang mana
    *   akan membuat sleep si intent yang sedang aktif dan karena ini MainActivity
    *   yang di berhentikan, android akan menganggap aplikasi tersebut berhenti merespon
    *   sehingga akan dipaksa untuk di tutup.
    *
    * */

    // public void mainButtonThreadClicked(View view){
    //     Runnable runnable = new Runnable() {
    //         @Override
    //         public void run() {
    //             int i = 0;
    //             while(i <= 3) {
    //                 try {
    //                     Thread.sleep(1000);
    //                     i++;
    //                 }
    //                 catch (InterruptedException e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //
    //             txtTulisan.setText("Setelah 3 detik kemudian");
    //         }
    //     };
    //
    //     Thread thread = new Thread(runnable);
    //     thread.start();
    // }

    /*
    *   Nomor 7:
    *   Only the original thread that created a view hierarchy can touch its views.
    *
    * */

    // public void mainButtonThreadClicked(View view){
    //     Runnable runnable = new Runnable() {
    //         @Override
    //         public void run() {
    //             int i = 0;
    //             while(i <= 3) {
    //                 try {
    //                     Thread.sleep(1000);
    //                     i++;
    //                 }
    //                 catch (InterruptedException e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //
    //             txtTulisan.setText("Setelah 3 detik kemudian");
    //         }
    //     };
    //
    //     runOnUiThread(runnable);
    // }

    public void mainButtonThreadClicked(View view) {
        MainAsyncTask runner = new MainAsyncTask();
        runner.execute(3);
    }

    private class MainAsyncTask extends AsyncTask<Integer, String, String> {

        private String response;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(Integer... integers) {
            int i = 0;

            while (i <= integers[0]) {
                try {
                    Thread.sleep(1000);
                    publishProgress("Waktu diam adalah " + i + " detik");
                    i++;

                    // Ini akan dikirim ke onPost Execute
                    response = "Selesai !";
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    response = e.getMessage();
                }
            }

            return response;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                "Progress Dialog",
                "Menunggu 3 detik"
            );
        }

        @Override
        protected void onPostExecute(String s) {
            if(progressDialog != null) {
                progressDialog.dismiss();
                txtTulisan.setText(s);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            txtTulisan.setText(values[0]);
        }
    }

}