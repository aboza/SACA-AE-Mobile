package com.example.sacaae_mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


import commons.*;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ComisionConsultationActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comision_consultation);
		/* Send the GetAllComision request to WCF service */
		new ComisionAsyncTask().execute(ConstantsFile.WCF_SERVICE_ADDRESS
				+ "getAllComision");

		/*OnClickListener for our Comision ListView, we get the info of the comision listview
		 * and create a new intent to show the comision detail */
		final ListView listview = (ListView) findViewById(R.id.ListView02);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Comision vComision = (Comision) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(), ComisionConsultationDetail.class);
				intent.putExtra("ComisionID",Integer.toString(vComision.getID()));
				intent.putExtra("ComisionName", vComision.getNombre());
				intent.putExtra("ComisionStartDate", vComision.getInicio());
				intent.putExtra("ComisionEndDate", vComision.getFin());
				startActivity(intent);

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comision_consultation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public ArrayList<Comision> FromJSONtoArrayList(String url) {
		ArrayList<Comision> comisionList = new ArrayList<Comision>();

		try {
			URL json = new URL(url);
			URLConnection jc = json.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					jc.getInputStream()));

			String line = reader.readLine();

			JSONObject jsonResponse = new JSONObject(line);
			JSONArray jsonArray = jsonResponse
					.getJSONArray("GetAllComisionResult");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jObject = (JSONObject) jsonArray.get(i);
				Comision vComision = new Comision();
				vComision.setID(jObject.getInt("ID"));
				vComision.setNombre(jObject.getString("Nombre"));
				vComision.setInicio(jObject.getString("Inicio"));
				vComision.setFin(jObject.getString("Fin"));
				comisionList.add(vComision);
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comisionList;
	}

	public class ComisionAsyncTask extends
			AsyncTask<String, Void, ArrayList<Comision>> {

		@Override
		protected ArrayList<Comision> doInBackground(String... url) {
			return FromJSONtoArrayList(url[0]);
		}

		protected void onPostExecute(ArrayList<Comision> comisionList) {
			ListView listView1 = (ListView) findViewById(R.id.ListView02);
			ArrayAdapter<Comision> adapter = new ArrayAdapter<Comision>(
					getApplicationContext(),
					R.layout.custom_text_view, comisionList);
			listView1.setAdapter(adapter);
		}

	}
}
