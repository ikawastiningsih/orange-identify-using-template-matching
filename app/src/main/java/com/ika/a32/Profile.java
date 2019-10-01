package com.ika.a32;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Profile extends AppCompatActivity {

    String ip="";
    String id_user0="";

    TextView txtnama_user;
    TextView txtjenis_kelamin;
    TextView txttanggal_lahir;
    TextView txtalamat_email;
    TextView txtusername;
    TextView txtpassword;



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
    private static final String TAG_password = "password";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ip=jsonParser.getIP();


        txtnama_user= (TextView) findViewById(R.id.txtnama_user);
        txttanggal_lahir= (TextView) findViewById(R.id.txttanggal_lahir);
        txtjenis_kelamin= (TextView) findViewById(R.id.txtjenis_kelamin);
        txtalamat_email = (TextView) findViewById(R.id.txtalamat_email);
        txtusername= (TextView) findViewById(R.id.txtusername);
        txtpassword= (TextView) findViewById(R.id.txtpassword);



        btnProses= (Button) findViewById(R.id.btnproses);
        btnHapus = (Button) findViewById(R.id.btnhapus);

        Intent i = getIntent();
        id_user0 = i.getStringExtra("pk");


        new Profile.get().execute();

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String lnama_user= txtnama_user.getText().toString();
                String ltanggal_lahir= txttanggal_lahir.getText().toString();
                String ljenis_kelamin= txtjenis_kelamin.getText().toString();
                String lalamat_email= txtalamat_email.getText().toString();
                String lusername= txtusername.getText().toString();
                String lpassword= txtpassword.getText().toString();



                if(lnama_user.length()<1){lengkapi("nama_user");}
                else if(ltanggal_lahir.length()<1){lengkapi("tanggal_lahir");}
                else if(ljenis_kelamin.length()<1){lengkapi("jenis_kelamin");}
                else if(lalamat_email.length()<1){lengkapi("alamat_email");}
                else if(lusername.length()<1){lengkapi("username");}
                else if(lpassword.length()<1){lengkapi("password");}
                else{

                    new Profile.update().execute();
                }//else

            }});

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
            pDialog = new ProgressDialog(Profile.this);
            pDialog.setMessage("Load data detail. Silahkan tunggu...");
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
                                txtpassword.setText(myJSON.getString(TAG_password));
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

    class update extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Profile.this);
            pDialog.setMessage("Mengubah data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        protected String doInBackground(String... args) {
            String lnama_user= txtnama_user.getText().toString();
            String ltanggal_lahir= txttanggal_lahir.getText().toString();
            String ljenis_kelamin= txtjenis_kelamin.getText().toString();
            String lalamat_email= txtalamat_email.getText().toString();
            String lusername= txtusername.getText().toString();
            String lpassword= txtpassword.getText().toString();



            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id_user", id_user0));
            params.add(new BasicNameValuePair("nama_user", lnama_user));
            params.add(new BasicNameValuePair("tanggal_lahir", ltanggal_lahir));
            params.add(new BasicNameValuePair("jenis_kelamin", ljenis_kelamin));
            params.add(new BasicNameValuePair("alamat_email", lalamat_email));
            params.add(new BasicNameValuePair("username", lusername));
            params.add(new BasicNameValuePair("password", lpassword));


            String url=ip+"user/user_update.php";
            Log.v("update",url);
            JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);
            Log.d("add", json.toString());
            try {
                int sukses = json.getInt(TAG_SUKSES);
                if (sukses == 1) {
                    Intent i = getIntent();
                    setResult(100, i);
                    finish();
                } else {
                    // gagal update data
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {pDialog.dismiss();}
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    public void lengkapi(String item){
        new AlertDialog.Builder(this)
                .setTitle("Lengkapi Data")
                .setMessage("Silakan lengkapi data "+item +" !")
                .setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {

                    }}).show();
    }




    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
