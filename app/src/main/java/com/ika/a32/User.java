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
import java.util.Calendar;
import java.util.List;

public class User extends Activity {
	String ip="";
	String id_user;
	String id_user0="";

	EditText txtid_user;
	EditText nama_user;
	EditText jenis_kelamin;
	EditText tanggal_lahir;
	EditText alamat_email;
	EditText username;
	EditText password;
	/*String jk;
	RadioGroup rg;*/

	Button btnSimpan;
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
		setContentView(R.layout.user);

		ip=jsonParser.getIP();
		callMarquee();

		txtid_user = (EditText) findViewById(R.id.id_user);
		nama_user= (EditText) findViewById(R.id.nama_user);

		// rg = (RadioGroup) findViewById(R.id.radio);

		tanggal_lahir = (EditText) findViewById(R.id.tanggal_lahir);
        alamat_email= (EditText) findViewById(R.id.alamat_email);
		jenis_kelamin= (EditText) findViewById(R.id.jenis_kelamin);
		username= (EditText) findViewById(R.id.username);
		password= (EditText) findViewById(R.id.password);

		btnSimpan= (Button) findViewById(R.id.btnSimpan);
		btnHapus = (Button) findViewById(R.id.btnHapus);

		Intent i = getIntent();
		id_user0 = i.getStringExtra("pk");
		id_user=id_user0;
		if(id_user0.length()>0){
			new get().execute();
			btnSimpan.setText("Update Data");
			btnHapus.setVisibility(View.VISIBLE);
		}
		else{
			btnSimpan.setText("Simpan");
			btnHapus.setVisibility(View.GONE);
		}

		btnSimpan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				//int selectedId = rg.getCheckedRadioButtonId();

				// find the radiobutton by returned id
				//RadioButton	radioButton = (RadioButton) findViewById(selectedId);

				//jk=radioButton.getText().toString();
				id_user= txtid_user.getText().toString();
				String lnama_user= nama_user.getText().toString();
				String ljenis_kelamin= jenis_kelamin.getText().toString();
				String ltanggal_lahir= tanggal_lahir.getText().toString();
                String lalamat_email= alamat_email.getText().toString();
                String lusernamel= username.getText().toString();
                String lpassword= password.getText().toString();

				if(id_user.length()<1){lengkapi("id_user");}
				else if(lnama_user.length()<1){lengkapi("nama_user");}
				else if(lalamat_email.length()<1){lengkapi("email");}
				else{
					if(id_user0.length()>0){
						new update().execute();
					}
					else{
						new save().execute();
					}
				}//else

			}});

	}

	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	class get extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(User.this);
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
								txtid_user.setText(id_user0);
								nama_user.setText(myJSON.getString(TAG_nama_user));
								jenis_kelamin.setText(myJSON.getString(TAG_jenis_kelamin));
								alamat_email.setText(myJSON.getString(TAG_alamat_email));
								tanggal_lahir.setText(myJSON.getString(TAG_tanggal_lahir));
								username.setText(myJSON.getString(TAG_username));
								password.setText(myJSON.getString(TAG_password));
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
			pDialog = new ProgressDialog(User.this);
			pDialog.setMessage("Menyimpan data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		protected String doInBackground(String... args) {
			id_user = txtid_user.getText().toString();
			String lnama_user= nama_user.getText().toString();
			String lalamat_email= alamat_email.getText().toString();
			String ljenis_kelamin= jenis_kelamin.getText().toString();
			String ltanggal_lahir= tanggal_lahir.getText().toString();
			String lusername= username.getText().toString();
			String lpassword= password.getText().toString();
			

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_user", id_user));
			params.add(new BasicNameValuePair("nama_user", lnama_user));
			params.add(new BasicNameValuePair("alamat_email", lalamat_email));
			params.add(new BasicNameValuePair("jenis_kelamin", ljenis_kelamin));
			params.add(new BasicNameValuePair("tanggal_lahir", ltanggal_lahir));
			params.add(new BasicNameValuePair("username", lusername));
			params.add(new BasicNameValuePair("password", lpassword));

			String url=ip+"user/user_add.php";
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
			pDialog = new ProgressDialog(User.this);
			pDialog.setMessage("Mengubah data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		protected String doInBackground(String... args) {
			id_user = txtid_user.getText().toString();
			String lnama_user= nama_user.getText().toString();
			String lalamat_email= alamat_email.getText().toString();
			String ljenis_kelamin= jenis_kelamin.getText().toString();
			String ltanggal_lahir= tanggal_lahir.getText().toString();
			String lusername= username.getText().toString();
			String lpassword= password.getText().toString();


			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_user0", id_user0));
			params.add(new BasicNameValuePair("id_user", id_user));
			params.add(new BasicNameValuePair("nama_user", lnama_user));
			params.add(new BasicNameValuePair("alamat_email", lalamat_email));
			params.add(new BasicNameValuePair("jenis_kelamin", ljenis_kelamin));
			params.add(new BasicNameValuePair("tanggal_lahir", ltanggal_lahir));
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
						finish();
					}}).show();
	}


	void callMarquee(){
		Calendar cal = Calendar.getInstance();
		int jam = cal.get(Calendar.HOUR);
		int menit= cal.get(Calendar.MINUTE);
		int detik= cal.get(Calendar.SECOND);

		int tgl= cal.get(Calendar.DATE);
		int bln= cal.get(Calendar.MONTH);
		int thn= cal.get(Calendar.YEAR);

		String stgl=String.valueOf(tgl)+"-"+String.valueOf(bln)+"-"+String.valueOf(thn);
		String sjam=String.valueOf(jam)+":"+String.valueOf(menit)+":"+String.valueOf(detik);

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
