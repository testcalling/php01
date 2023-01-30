package com.example.naucitenjemacki;

import java.util.ArrayList;
import java.util.List;


import naucitenjemacki.parser.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;





import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.Spanned;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class TablesActivity extends Activity{

	private String[] items;  
    private List<String> list;  
    private ArrayAdapter<String> adapter;  
    private int position;  
    TextView tvTable;
    

    private static String url = "http://naucitenjemacki.we.bs/get_tabele_a.php";
    
    JSONParser jParser = new JSONParser();
	 JSONArray lessons = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tables);
	
		 tvTable = (TextView)findViewById(R.id.table);
	
		 buttonLekcije();
	
         new LoadAllTables().execute();
         
         buttonMenu();
	}
	
	public void buttonLekcije()
	{
		Button butonLekcije=(Button)findViewById(R.id.tablesChoice);
		butonLekcije.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			selectTable();
				
			}
		});
		
	}

	String ID="1";
	
	
	class LoadAllTables extends AsyncTask<String, String, String> {

		ProgressDialog pDialog;
		
		String text,naziv,result;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(TablesActivity.this);
			pDialog.setMessage("Molimo pričekajte...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
	   
		protected String doInBackground(String... args) {
	
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("ID", ID));
			
			JSONObject json = jParser.makeHttpRequest(url, "POST", params);
			System.out.println(json.toString());
			try {

				int success = json.getInt("success");

				if (success == 1) {
				
					lessons = json.getJSONArray("TABELA");
					
					for (int i = 0; i < lessons.length(); i++) {
						JSONObject c = lessons.getJSONObject(i);

						
						naziv = c.getString("NAZIV");
						text = c.getString("TEKST");
						
					
				        result= naziv+"\n\n"+text;
						
					
					}
				}
				else
				{
					TablesActivity.this.runOnUiThread(new Runnable() {

				        public void run() {
				          
				            	 Toast toast = Toast.makeText(getApplicationContext(), "Nema podataka", Toast.LENGTH_SHORT);
				            	 toast.setGravity(Gravity.CENTER, 0, 0);
				                 toast.show();
				            	   		
				            
				        }
				    });
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return result;
		}

		
		protected void onPostExecute(String file_url) {
		
			pDialog.dismiss();
			tvTable.setText(file_url);

		}

	}
	AlertDialog m_Alert;
	 private void selectTable(){
	    	final CharSequence[] items = {"1.Tabela","2.Tabela","3.Tabela","4.Tabela","5.Tabela","6.Tabela","7.Tabela","8.Tabela","9.Tabela","10.Tabela","11.Tabela","12.Tabela","13.Tabela","14.Tabela","15.Tabela","16.Tabela","17.Tabela","18.Tabela","19.Tabela"};

	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setTitle("Izbor Tabele");
	    	builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
	    	    public void onClick(DialogInterface dialog, int item) {
	    	  
	    	    
	    	    	switch( item ){
	    	    	
	    	    	case 0:
	    	    		ID="1";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 1:
	    	    		ID="2";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 2:
	    	    		ID="3";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 3:
	    	    		ID="4";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 4:
	    	    		ID="5";
	    	    		new LoadAllTables().execute();
	    	    	case 5:
	    	    		ID="6";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 6:
	    	    		ID="7";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 7:
	    	    		ID="8";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 8:
	    	    		ID="9";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 9:
	    	    		ID="10";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 10:
	    	    		ID="11";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 11:
	    	    		ID="12";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 12:
	    	    		ID="13";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 13:
	    	    		ID="14";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 14:
	    	    		ID="15";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 15:
	    	    		ID="16";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 16:
	    	    		ID="17";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 17:
	    	    		ID="18";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	case 18:
	    	    		ID="19";
	    	    		new LoadAllTables().execute();
	    	    		break;
	    	    	
	    	    		default:
	    	    			break;
	    	    	}
	    	    	
	    	    	m_Alert.dismiss();
	    	 
	    	    }
	    	});
	    	m_Alert = builder.create();  
	    	m_Alert.show();
	    }   
	 public void buttonMenu()
		{
			Button butonLekcije=(Button)findViewById(R.id.optionMenuT);
			butonLekcije.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				optionMenu();
					
				}
			});
			
		}
	 AlertDialog m_Alert1;
	 private void optionMenu(){
	    	final CharSequence[] items = {"Početna",
                 "Lekcije",
                 "Tabele",
                 "Ostalo",
                 "Savjeti",
                 "Novosti",
                 "Kontakt"};

	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setTitle("Menu");
	    	builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
	    	    public void onClick(DialogInterface dialog, int item) {
	    	  
	    	    
	    	    	switch( item ){
	    	    	case 0: 
	                	   Intent intent0 = new Intent(TablesActivity.this,MainActivity.class);
	               		   startActivity(intent0); 
	                	   break;
	                   case 1:
	                	   Intent intent = new Intent(TablesActivity.this,LessonsActivity.class);
	               		   startActivity(intent);  
	                   case 2:
	                	 
	                	   /*Intent intent1 = new Intent(TablesActivity.this,TablesActivity.class);
	               		   startActivity(intent1); */
	               		  break;
	                   case 3:
	                	   Intent intent3 = new Intent(TablesActivity.this,OthersActivity.class);
	               		   startActivity(intent3); 
	                	   break;
	                   case 4:
	                  	 
	                	   Intent intent4 = new Intent(TablesActivity.this,TipsActivity.class);
	               		   startActivity(intent4); 
	               		  break;
	                   case 5:
	                    	 
	                	   Intent intent5 = new Intent(TablesActivity.this,NewsActivity.class);
	               		   startActivity(intent5); 
	               		  break;
	                   case 6:
	                  	 
	                	   Intent intent6 = new Intent(TablesActivity.this,ContactActivity.class);
	               		   startActivity(intent6); 
	               		   
	               		  break;
	               	   default:
	               		   break;
	    	    	}
	    	    	
	    	    	m_Alert1.dismiss();
	    	 
	    	    }
	    	});
	    	m_Alert1 = builder.create();  
	    	m_Alert1.show();
	    }   

}
