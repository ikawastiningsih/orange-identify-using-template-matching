package com.ika.a32;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Hasil extends Activity {
	String ip="";
	ImageView myGambar;

	EditText txt1;
	EditText txt2;
	EditText txt3;
    EditText txt4;


	Button btnProses;
	Button btnHapus;

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();

	String keterangan,kategori,analisa, nmin;


	String id_user="",nama_user="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hasil);

		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Hasil.this);
		Boolean Registered = sharedPref.getBoolean("Registered", false);
		if (!Registered) {
			finish();
		} else {
			id_user = sharedPref.getString("id_user", "");
			nama_user = sharedPref.getString("nama_user", "");
		}


		ip=jsonParser.getIP();


		txt1 = (EditText) findViewById(R.id.txt1);txt1.setEnabled(false);
		txt2= (EditText) findViewById(R.id.txt2);txt2.setEnabled(false);
		txt3= (EditText) findViewById(R.id.txt3);txt3.setEnabled(false);
        txt4= (EditText) findViewById(R.id.txt4);txt4.setEnabled(false);

		myGambar= (ImageView) findViewById(R.id.myGambar);

		btnProses= (Button) findViewById(R.id.btnproses);
		btnHapus = (Button) findViewById(R.id.btnhapus);

		Intent i = getIntent();
		keterangan= i.getStringExtra("keterangan");
		kategori= i.getStringExtra("kategori");
		analisa= i.getStringExtra("analisa");
        nmin= i.getStringExtra("nmin");



        int gb=R.drawable.bm1;
		if(kategori.equalsIgnoreCase("bm1.jpg")){//bm1.jpg
			gb=R.drawable.bm1;
		}
		else if(kategori.equalsIgnoreCase("bm2.jpg")){
			gb=R.drawable.bm2;
		}
		else if(kategori.equalsIgnoreCase("bm3.jpg")){
			gb=R.drawable.bm3;
		}
		else if(kategori.equalsIgnoreCase("bm4.jpg")){
			gb=R.drawable.bm4;
		}
		else if(kategori.equalsIgnoreCase("bm5.jpg")){
			gb=R.drawable.bm5;
		}
		else if(kategori.equalsIgnoreCase("bm6.jpg")){
			gb=R.drawable.bm6;
		}
		else if(kategori.equalsIgnoreCase("bm7.jpg")){
			gb=R.drawable.bm7;
		}
		else if(kategori.equalsIgnoreCase("bm8.jpg")){
			gb=R.drawable.bm8;
		}
		else if(kategori.equalsIgnoreCase("bm9.jpg")){
			gb=R.drawable.bm9;
		}
		else if(kategori.equalsIgnoreCase("bm10.jpg")){
			gb=R.drawable.bm10;
		}
		else if(kategori.equalsIgnoreCase("bm11.jpg")){
			gb=R.drawable.bm11;
		}
		else if(kategori.equalsIgnoreCase("bm12.jpg")){
			gb=R.drawable.bm12;
		}
		else if(kategori.equalsIgnoreCase("bm13.jpg")){
			gb=R.drawable.bm13;
		}
		else if(kategori.equalsIgnoreCase("bm14.jpg")){
			gb=R.drawable.bm14;
		}
		else if(kategori.equalsIgnoreCase("bm15.jpg")){
			gb=R.drawable.bm15;
		}
		else if(kategori.equalsIgnoreCase("bm16.jpg")){
			gb=R.drawable.bm16;
		}
		else if(kategori.equalsIgnoreCase("bm17.jpg")){
			gb=R.drawable.bm17;
		}
		else if(kategori.equalsIgnoreCase("bm18.jpg")){
			gb=R.drawable.bm18;
		}
		else if(kategori.equalsIgnoreCase("bm19.jpg")){
			gb=R.drawable.bm19;
		}
		else if(kategori.equalsIgnoreCase("bm20.jpg")){
			gb=R.drawable.bm20;
		}
        else if(kategori.equalsIgnoreCase("bm21.jpg")){
            gb=R.drawable.bm21;
        }
        else if(kategori.equalsIgnoreCase("bm22.jpg")){
            gb=R.drawable.bm22;
        }
        else if(kategori.equalsIgnoreCase("bm23.jpg")){
            gb=R.drawable.bm23;
        }
        else if(kategori.equalsIgnoreCase("bm24.jpg")){
            gb=R.drawable.bm24;
        }
        else if(kategori.equalsIgnoreCase("bm25.jpg")){
            gb=R.drawable.bm25;
        }
        else if(kategori.equalsIgnoreCase("bm26.jpg")){
            gb=R.drawable.bm26;
        }
        else if(kategori.equalsIgnoreCase("bm28.jpg")){
            gb=R.drawable.bm28;
        }
        else if(kategori.equalsIgnoreCase("bm29.jpg")){
            gb=R.drawable.bm29;
        }
        else if(kategori.equalsIgnoreCase("bm30.jpg")){
            gb=R.drawable.bm30;
        }
		else if(kategori.equalsIgnoreCase("b31.jpg")){
			gb=R.drawable.bm31;
		}
		else if(kategori.equalsIgnoreCase("bm33.jpg")){
			gb=R.drawable.bm33;
		}
		else if(kategori.equalsIgnoreCase("bm34.jpg")){
			gb=R.drawable.bm34;
		}
		else if(kategori.equalsIgnoreCase("bm35.jpg")){
			gb=R.drawable.bm35;
		}
		else if(kategori.equalsIgnoreCase("m1.jpg")){
			gb=R.drawable.m1;
		}
		else if(kategori.equalsIgnoreCase("m2.jpg")){
			gb=R.drawable.m2;
		}
		else if(kategori.equalsIgnoreCase("m3.jpg")){
			gb=R.drawable.m3;
		}
		else if(kategori.equalsIgnoreCase("m4.jpg")){
			gb=R.drawable.m4;
		}
		else if(kategori.equalsIgnoreCase("m5.jpg")){
			gb=R.drawable.m5;
		}
		else if(kategori.equalsIgnoreCase("m6.jpg")){
			gb=R.drawable.m6;
		}
		else if(kategori.equalsIgnoreCase("m7.jpg")){
			gb=R.drawable.m7;
		}
		else if(kategori.equalsIgnoreCase("m8.jpg")){
			gb=R.drawable.m8;
		}
		else if(kategori.equalsIgnoreCase("m9.jpg")){
			gb=R.drawable.m9;
		}
		else if(kategori.equalsIgnoreCase("m10.jpg")){
			gb=R.drawable.m10;
		}
		else if(kategori.equalsIgnoreCase("m11.jpg")){
			gb=R.drawable.m11;
		}
		else if(kategori.equalsIgnoreCase("m12.jpg")){
			gb=R.drawable.m12;
		}
		else if(kategori.equalsIgnoreCase("m13.jpg")){
			gb=R.drawable.m13;
		}
		else if(kategori.equalsIgnoreCase("m14.jpg")){
			gb=R.drawable.m14;
		}
		else if(kategori.equalsIgnoreCase("m15.jpg")){
			gb=R.drawable.m15;
		}
		else if(kategori.equalsIgnoreCase("m16.jpg")){
			gb=R.drawable.m16;
		}
		else if(kategori.equalsIgnoreCase("m17.jpg")){
			gb=R.drawable.m17;
		}
		else if(kategori.equalsIgnoreCase("m18.jpg")){
			gb=R.drawable.m18;
		}
		else if(kategori.equalsIgnoreCase("m19.jpg")){
			gb=R.drawable.m19;
		}
		else if(kategori.equalsIgnoreCase("m20.jpg")){
			gb=R.drawable.m20;
		}
		else if(kategori.equalsIgnoreCase("m21.jpg")){
			gb=R.drawable.m21;
		}
		else if(kategori.equalsIgnoreCase("m22.jpg")){
			gb=R.drawable.m22;
		}
		else if(kategori.equalsIgnoreCase("m23.jpg")){
			gb=R.drawable.m23;
		}
		else if(kategori.equalsIgnoreCase("m25.jpg")){
			gb=R.drawable.m25;
		}
		else if(kategori.equalsIgnoreCase("m24.jpg")){
			gb=R.drawable.m24;
		}
		else if(kategori.equalsIgnoreCase("m26.jpg")){
			gb=R.drawable.m26;
		}
		else if(kategori.equalsIgnoreCase("m28.jpg")){
			gb=R.drawable.m27;
		}
		else if(kategori.equalsIgnoreCase("m28.jpg")){
			gb=R.drawable.m28;
		}
		else if(kategori.equalsIgnoreCase("m30.jpg")){
			gb=R.drawable.m30;
		}
		else if(kategori.equalsIgnoreCase("m31.jpg")){
			gb=R.drawable.m30;
		}
		else if(kategori.equalsIgnoreCase("m32.jpg")){
			gb=R.drawable.m31;
		}
		else if(kategori.equalsIgnoreCase("m33.jpg")){
			gb=R.drawable.m32;
		}
		else if(kategori.equalsIgnoreCase("m34.jpg")){
			gb=R.drawable.m33;
		}
		else if(kategori.equalsIgnoreCase("m35.jpg")){
			gb=R.drawable.m34;
		}
		else if(kategori.equalsIgnoreCase("m36.jpg")){
			gb=R.drawable.m35;
		}
		else if(kategori.equalsIgnoreCase("kl1.jpg")){
			gb=R.drawable.kl1;
		}
		else if(kategori.equalsIgnoreCase("kl2.jpg")){
			gb=R.drawable.kl2;
		}
		else if(kategori.equalsIgnoreCase("kl3.jpg")){
			gb=R.drawable.kl3;
		}
		else if(kategori.equalsIgnoreCase("kl4.jpg")){
			gb=R.drawable.kl4;
		}
		else if(kategori.equalsIgnoreCase("kl5.jpg")){
			gb=R.drawable.kl5;
		}
		else if(kategori.equalsIgnoreCase("kl6.jpg")){
			gb=R.drawable.kl6;
		}
		else if(kategori.equalsIgnoreCase("kl7.jpg")){
			gb=R.drawable.kl7;
		}
		else if(kategori.equalsIgnoreCase("kl8.jpg")){
			gb=R.drawable.kl8;
		}
		else if(kategori.equalsIgnoreCase("kl9.jpg")){
			gb=R.drawable.kl9;
		}
		else if(kategori.equalsIgnoreCase("kl10.jpg")){
			gb=R.drawable.kl10;
		}
		else if(kategori.equalsIgnoreCase("kl11.jpg")){
			gb=R.drawable.kl11;
		}
		else if(kategori.equalsIgnoreCase("kl12.jpg")){
			gb=R.drawable.kl12;
		}
		else if(kategori.equalsIgnoreCase("kl13.jpg")){
			gb=R.drawable.kl13;
		}
		else if(kategori.equalsIgnoreCase("kl14.jpg")){
			gb=R.drawable.kl14;
		}
		else if(kategori.equalsIgnoreCase("kl15.jpg")){
			gb=R.drawable.kl15;
		}
		else if(kategori.equalsIgnoreCase("kl16.jpg")){
			gb=R.drawable.kl16;
		}
		else if(kategori.equalsIgnoreCase("kl17.jpg")){
			gb=R.drawable.kl17;
		}
		else if(kategori.equalsIgnoreCase("kl18.jpg")){
			gb=R.drawable.kl18;
		}
		else if(kategori.equalsIgnoreCase("kl19.jpg")){
			gb=R.drawable.kl19;
		}
		else if(kategori.equalsIgnoreCase("kl20.jpg")){
			gb=R.drawable.kl20;
		}
		else if(kategori.equalsIgnoreCase("kl21.jpg")){
			gb=R.drawable.kl21;
		}
		else if(kategori.equalsIgnoreCase("kl22.jpg")){
			gb=R.drawable.kl22;
		}
		else if(kategori.equalsIgnoreCase("kl23.jpg")){
			gb=R.drawable.kl23;
		}
		else if(kategori.equalsIgnoreCase("kl24.jpg")){
			gb=R.drawable.kl24;
		}
		else if(kategori.equalsIgnoreCase("kl25.jpg")){
			gb=R.drawable.kl25;
		}
		else if(kategori.equalsIgnoreCase("kl26.jpg")){
			gb=R.drawable.kl26;
		}
		else if(kategori.equalsIgnoreCase("kl27.jpg")){
			gb=R.drawable.kl27;
		}
		else if(kategori.equalsIgnoreCase("kl28.jpg")){
			gb=R.drawable.kl28;
		}
		else if(kategori.equalsIgnoreCase("kl29.jpg")){
			gb=R.drawable.kl29;
		}
		else if(kategori.equalsIgnoreCase("kl31.jpg")){
			gb=R.drawable.kl31;
		}
		else if(kategori.equalsIgnoreCase("kl32.jpg")){
			gb=R.drawable.kl32;
		}
		else if(kategori.equalsIgnoreCase("kl33.jpg")){
			gb=R.drawable.kl33;
		}
		else if(kategori.equalsIgnoreCase("kl34.jpg")){
			gb=R.drawable.kl34;
		}


		myGambar.setImageResource(gb);
		txt1.setText(nama_user);
		txt2.setText(analisa);
		txt3.setText(keterangan);
        txt4.setText(nmin);

		btnProses.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
		new save().execute();


			}});

		btnHapus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
			finish();
			}});
	}


	class save extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Hasil.this);
			pDialog.setMessage("Menyimpan data");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		protected String doInBackground(String... args) {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_user", id_user));
			params.add(new BasicNameValuePair("analisa", analisa));
			params.add(new BasicNameValuePair("kategori", kategori));
			params.add(new BasicNameValuePair("keterangan", keterangan));
            params.add(new BasicNameValuePair("nmin", nmin));

			String url=ip+"simpan.php";
			Log.v("add",url);
			JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);
			Log.d("add", json.toString());
			try {
				int sukses = json.getInt("sukses");
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
						finish();
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
