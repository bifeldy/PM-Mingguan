package id.ac.umn.week10_00000013536;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import id.ac.umn.week10_00000013536.POJO.Identity;

public class MainActivity extends AppCompatActivity {

    // Data yang digunakan dan dimasukkan kedalam TextView
    private ArrayList<Identity> identities;

    // TextView yang digunakan untuk menampilkan data
    private TextView tvMain;

    private class FetchData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;

            // Hasil dari JSON akan disimpan disini
            String jsonString = null;

            try {
                // Gunakan sesuai dengan kebutuhan
                // Pada URL String yang dibutuhkan adalah loggeduser dan parameter yang digunakan

                // Wajib menuliskan protocol yang digunakan (http:// atau https://)
                // Namun pada server yang disediakan untuk Web API sederhana ini hanya dapat menggunakan http://

                String urlString = "http://10.0.1.31:10000/requestdata?loggeduser=" + strings[0];

                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();

                // Method yang diperbolehkan pada Web API,
                // Pada API yang disediakan hanya menerima method GET
                urlConnection.setRequestMethod("GET");

                // Buka koneksinya sesuai dengan URL yang sudah disediakan
                urlConnection.connect();

                int lengthOfFile = urlConnection.getContentLength();

                // Baca data output dari Web API sebagai string terlebih dahulu
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();

                // Langsung tidak mengembalikan apapun bila tidak ada output yang didapat dari Web API
                if(inputStream == null) {
                    return null;
                }

                // Masukkan hasil dari Web API kedalam bufferd reader
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                // Penampung untuk dibaca line by line
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    // Isi data keluaran dari Web API ke dalam stringBuffer
                    stringBuffer.append(line + "\n");
                }

                // Apabila data keluaran tidak memiliki isi
                if(stringBuffer.length() == 0) {
                    return  null;
                }

                // Jadikan data keluaran ke dalam variabel output yang seharusnya
                // Konversikan dalam bentuk string
                jsonString = stringBuffer.toString();

                // Output dapat dilihat pada Logcat
                Log.d("FETCHDATA", jsonString);

                // Konversi dari string JSON yang akan digunakan pada UI
                JSONObject jsonObject = new JSONObject(jsonString);

                int statusCode = jsonObject.getInt("status");

                if(statusCode == 200) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    // Looping data Web API
                    for(int i=0; i<jsonArray.length(); i++) {
                        // Mendapatkan JSON "status", "message', dan "data"
                        // JSON "data" merupakan array of data yang akan kita gunakan.
                        JSONObject theData = jsonArray.getJSONObject(i);

                        Identity identity = new Identity();
                        identity.setId(theData.getInt("id"));
                        identity.setFirst_name(theData.getString("first_name"));
                        identity.setLast_name(theData.getString("last_name"));
                        identity.setIp_address(theData.getString("ip_address"));
                        identity.setMac_address(theData.getString("mac_address"));
                        identity.setUrl_website(theData.getString("url_website"));

                        identities.add(identity);
                    }
                }

            } catch (MalformedURLException e) {
                Log.e("MALFORMED", "MalformedURLException: " + e.getMessage());
            } catch (IOException e) {
                Log.e("IO", "IOException: " + e.getMessage());
            } catch (JSONException e) {
                Log.e("JSON", "JSONException: " + e.getMessage());
            } finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }

                if(bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    }
                    catch (IOException e) {
                        Log.e("BUFFEREDIOEXCEPTION", "IOException: " + e.getMessage());
                    }
                }
            }

            return jsonString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // Apabila menggunakan konsep adapter, pada tahap ini adapter harus di refresh
            // adapter.notifyDataSetChanged(); <-- Kalo Pakai Adapter

            // String dataToPlace = identities.get(1).getFirst_name() + " " + identities.get(1).getLast_name();
            // Jawaban Nomor 7 -- Carly Stode

            // Jawaban Nomor 8
            String dataToPlace = "";
            for(int i=1; i<=5; i++) {
                dataToPlace += ("[" + identities.get(i).getFirst_name() + " " + identities.get(i).getLast_name() + "] - [" + identities.get(i).getIp_address() + "]\n");
            }

            tvMain.setText(dataToPlace);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        identities = new ArrayList<>();
        tvMain = findViewById(R.id.main_text_jsondata);

        // "user" ini merupakan value yang dibutuhkan oleh parameter loggeduser pada Web API di endpoint /requestdata
        // [IP]/requestdata?loggeduser=user
        new FetchData().execute("user");

        /*
            Jawaban Nomor 8 -- Ya Data Muncul Di Logcat (Kepanjangan .. Wkwkwk)
            04-16 16:38:08.418 6118-6144/id.ac.umn.week10_00000013536 D/FETCHDATA: {"status":200,"message":"Requested data","data":[{"id":0,"first_name":.........
        */
    }
}
