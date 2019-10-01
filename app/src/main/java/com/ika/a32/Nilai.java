package com.ika.a32;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Nilai extends Activity {
	String ip="";
	String id_nilai;
	String id_nilai0="";

	EditText txtid_nilai;
	EditText txtnilai_r;
	EditText txtnilai_g;
	EditText txtnilai_b;
	EditText txttingkat_kematangan;


	Button btnProses;
	Button btnHapus;

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();

	private static final String TAG_SUKSES = "sukses";
	private static final String TAG_record = "record";

	private static final String TAG_nilai_r = "nilai_r";
	private static final String TAG_nilai_g = "nilai_g";
	private static final String TAG_nilai_b = "nilai_b";
	private static final String TAG_tingkat_kematangan = "tingkat_kematangan";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nilai);

		ip=jsonParser.getIP();


		txtid_nilai = (EditText) findViewById(R.id.txtid_nilai);
		txtnilai_r= (EditText) findViewById(R.id.txtnilai_r);
		txtnilai_b= (EditText) findViewById(R.id.txtnilai_b);
		txtnilai_g= (EditText) findViewById(R.id.txtnilai_g);
		txttingkat_kematangan = (EditText) findViewById(R.id.txttingkat_kematangan);


		btnProses= (Button) findViewById(R.id.btnproses);
		btnHapus = (Button) findViewById(R.id.btnhapus);

		Intent i = getIntent();
		id_nilai0 = i.getStringExtra("pk");

		if(id_nilai0.length()>0){
			new get().execute();
			btnProses.setText("Update Data");
			btnHapus.setVisibility(View.VISIBLE);
		}
		else{
			btnProses.setText("Add New Data");
			btnHapus.setVisibility(View.GONE);
		}

		btnProses.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				id_nilai= txtid_nilai.getText().toString();
				String lnilai_r= txtnilai_r.getText().toString();
				String lnilai_b= txtnilai_b.getText().toString();
				String lnilai_g= txtnilai_g.getText().toString();
				String ltingkat_kematangan= txttingkat_kematangan.getText().toString();

				if(id_nilai.length()<1){lengkapi("id_nilai");}
				else if(lnilai_r.length()<1){lengkapi("nilai_r");}
				else if(lnilai_b.length()<1){lengkapi("nilai_b");}
				else if(lnilai_g.length()<1){lengkapi("telpon");}
				else{
					if(id_nilai0.length()>0){
						new update().execute();
					}
					else{
						new save().execute();
					}
				}//else

			}});

		btnHapus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new del().execute();
			}});
	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	class get extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Nilai.this);
			pDialog.setMessage("Load data detail. Silahkan tunggu...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		protected String doInBackground(String... params) {
			int sukses;
			try {
				List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("id_nilai", id_nilai0));

				String url=ip+"nilai/nilai_detail.php";
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
								txtid_nilai.setText(id_nilai0);
								txtnilai_r.setText(myJSON.getString(TAG_nilai_r));
								txtnilai_g.setText(myJSON.getString(TAG_nilai_g));
								txtnilai_b.setText(myJSON.getString(TAG_nilai_b));
								txttingkat_kematangan.setText(myJSON.getString(TAG_tingkat_kematangan));

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

	class save extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Nilai.this);
			pDialog.setMessage("Menyimpan data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		protected String doInBackground(String... args) {
			id_nilai = txtid_nilai.getText().toString();
			String lnilai_r= txtnilai_r.getText().toString();
			String lnilai_b= txtnilai_b.getText().toString();
			String lnilai_g= txtnilai_g.getText().toString();
			String ltingkat_kematangan= txttingkat_kematangan.getText().toString();


			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_nilai0", id_nilai0));
			params.add(new BasicNameValuePair("id_nilai", id_nilai));
			params.add(new BasicNameValuePair("nilai_r", lnilai_r));
			params.add(new BasicNameValuePair("nilai_b", lnilai_b));
			params.add(new BasicNameValuePair("nilai_g", lnilai_g));
			params.add(new BasicNameValuePair("tingkat_kematangan", ltingkat_kematangan));

			String url=ip+"nilai/nilai_add.php";
			Log.v("add",url);
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

	class update extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Nilai.this);
			pDialog.setMessage("Mengubah data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		protected String doInBackground(String... args) {
			id_nilai = txtid_nilai.getText().toString();
			String lnilai_r= txtnilai_r.getText().toString();
			String lnilai_b= txtnilai_b.getText().toString();
			String lnilai_g= txtnilai_g.getText().toString();
			String ltingkat_kematangan= txttingkat_kematangan.getText().toString();


			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_nilai0", id_nilai0));
			params.add(new BasicNameValuePair("id_nilai", id_nilai));
			params.add(new BasicNameValuePair("nilai_r", lnilai_r));
			params.add(new BasicNameValuePair("nilai_b", lnilai_b));
			params.add(new BasicNameValuePair("nilai_g", lnilai_g));
			params.add(new BasicNameValuePair("tingkat_kematangan", ltingkat_kematangan));


			String url=ip+"nilai/nilai_update.php";
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

	class del extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Nilai.this);
			pDialog.setMessage("Menghapus data...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			int sukses;
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("id_nilai", id_nilai0));

				String url=ip+"nilai/nilai_del.php";
				Log.v("delete",url);
				JSONObject json = jsonParser.makeHttpRequest(url, "GET", params);
				Log.d("delete", json.toString());
				sukses = json.getInt(TAG_SUKSES);
				if (sukses == 1) {
					Intent i = getIntent();
					setResult(100, i);
					finish();
				}
			}
			catch (JSONException e) {e.printStackTrace();}
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
