package com.ika.a32;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Profil extends AppCompatActivity {

    String ip="";
    String id_user0="";
    String id_user="",nama_user,alamat_email;

    TextView txtnama_user;
    TextView txtjenis_kelamin;
    TextView txttanggal_lahir;
    TextView txtalamat_email;
    TextView txtusername;


    Button btnProses;
    Button btnHapus;

    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    private static final String TAG_SUKSES = "sukses";
    private static final String TAG_record = "record";

    private static final String TAG_nama_user = "nama_user";
    private static final String TAG_jenis_kelamin = "jenis_kelamin";
    private static final String TAG_tanggal_lahir = "tanggal_lahir";
    private static final String TAG_alamat_email = "alamat_email";
    private static final String TAG_username = "username";

  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Button btnedit= (Button) findViewById(R.id.btnedit);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(Profil.this,Profile.class);
                i.putExtra("pk","");
                startActivity(i);


            }

        });


        ip=jsonParser.getIP();


        txtnama_user= (TextView) findViewById(R.id.txtnama_user);
        txttanggal_lahir= (TextView) findViewById(R.id.txttanggal_lahir);
        txtjenis_kelamin= (TextView) findViewById(R.id.txtjenis_kelamin);
        txtalamat_email = (TextView) findViewById(R.id.txtalamat_email);
        txtusername= (TextView) findViewById(R.id.txtusername);


        btnProses= (Button) findViewById(R.id.btnproses);
        btnHapus = (Button) findViewById(R.id.btnhapus);

        Intent i = getIntent();
        id_user0 = i.getStringExtra("pk");


        new get().execute();


        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }});
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    class get extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profil.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... params) {
            int sukses;
            try {
                List<NameValuePair> params1 = new ArrayList<NameValuePair>();
                params1.add(new BasicNameValuePair("id_user", id_user0));

                String url=ip+"user/user_detail.php";
                Log.v("detail",url);
                JSONObject json = jsonParser.makeHttpRequest(url, "GET", params1);
                Log.d("detail", json.toString());
                sukses = json.getInt(TAG_SUKSES);
                if (sukses == 1) {
                    JSONArray myObj = json.getJSONArray(TAG_record); // JSON Array
                    final JSONObject myJSON = myObj.getJSONObject(0);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                txtnama_user.setText(myJSON.getString(TAG_nama_user));
                                txtjenis_kelamin.setText(myJSON.getString(TAG_jenis_kelamin));
                                txttanggal_lahir.setText(myJSON.getString(TAG_tanggal_lahir));
                                txtalamat_email.setText(myJSON.getString(TAG_alamat_email));
                                txtusername.setText(myJSON.getString(TAG_username));

                            }
                            catch (JSONException e) {e.printStackTrace();}
                        }});
                }
                else{
                    // jika id tidak ditemukan
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {pDialog.dismiss();}
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++







    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
