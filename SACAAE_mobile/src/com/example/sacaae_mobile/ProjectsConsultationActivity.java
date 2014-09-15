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

public class ProjectsConsultationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projects_consultation);
		/* Send the GetAllprojects request to WCF service */
		new ProjectsAsyncTask().execute(ConstantsFile.WCF_SERVICE_ADDRESS
				+ "getAllProjects");

		/*OnClickListener for our Projects ListView, we get the info of the project listview
		 * and create a new intent to show the project detail */
		final ListView listview = (ListView) findViewById(R.id.ListView01);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final Project vProject = (Project) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(), ProjectConsultationDetail.class);
				intent.putExtra("ProjectID",Integer.toString(vProject.getID()));
				intent.putExtra("ProjectName", vProject.getNombre());
				intent.putExtra("ProjectStartDate", vProject.getInicio());
				intent.putExtra("ProjectEndDate", vProject.getFin());
				intent.putExtra("ProjectLink", vProject.getLink());
				startActivity(intent);

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.projects_consultation, menu);
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

	public ArrayList<Project> FromJSONtoArrayList(String url) {
		ArrayList<Project> projectsList = new ArrayList<Project>();

		try {
			URL json = new URL(url);
			URLConnection jc = json.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					jc.getInputStream()));

			String line = reader.readLine();

			JSONObject jsonResponse = new JSONObject(line);
			JSONArray jsonArray = jsonResponse
					.getJSONArray("GetAllProjectsResult");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jObject = (JSONObject) jsonArray.get(i);
				Project vProject = new Project();
				vProject.setID(jObject.getInt("ID"));
				vProject.setNombre(jObject.getString("Nombre"));
				vProject.setInicio(jObject.getString("Inicio"));
				vProject.setFin(jObject.getString("Fin"));
				vProject.setLink(jObject.getString("Link"));
				projectsList.add(vProject);
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return projectsList;
	}

	public class ProjectsAsyncTask extends
			AsyncTask<String, Void, ArrayList<Project>> {

		@Override
		protected ArrayList<Project> doInBackground(String... url) {
			return FromJSONtoArrayList(url[0]);
		}

		protected void onPostExecute(ArrayList<Project> projectsList) {
			ListView listView1 = (ListView) findViewById(R.id.ListView01);
			listView1.setAdapter(new ArrayAdapter<Project>(
					getApplicationContext(),
					android.R.layout.simple_list_item_1, projectsList));
		}

	}
}
