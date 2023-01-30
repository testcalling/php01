package com.example.naucitenjemacki;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class LessonsActivity extends Activity{

	private String[] items;  
    private List<String> list;  
    private ArrayAdapter<String> adapter;  
    private int position;  
    TextView tvLesson;
    
    JSONParser jParser = new JSONParser();


    private static String url_all_lessons = "http://naucitenjemacki.we.bs/get_lekcije_a.php";

    JSONArray lessons = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lessons);

        
		tvLesson = (TextView)findViewById(R.id.lesson);
	
		 buttonLekcije();
		 new LoadAlllessons().execute();
		 buttonMenu();
	}
	
	public void buttonLekcije()
	{
		Button butonLekcije=(Button)findViewById(R.id.lessonsChoice);
		butonLekcije.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			selectLesson();
				
			}
		});
		
	}

	String IDTEXT="100";
	class LoadAlllessons extends AsyncTask<String, String, String> {

		ProgressDialog pDialog;
		
		String ttext,tnaziv,gtext,gnaziv,vnaziv,vtext,rvnaziv,rvtext,result;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(LessonsActivity.this);
			pDialog.setMessage("Molimo pričekajte...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
	
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("IDTEXT", IDTEXT));
			
			JSONObject json = jParser.makeHttpRequest(url_all_lessons, "POST", params);
			System.out.println(json.toString());
			try {

				int success = json.getInt("success");

				if (success == 1) {
				
					lessons = json.getJSONArray("TEXT");
					
					for (int i = 0; i < lessons.length(); i++) {
						JSONObject c = lessons.getJSONObject(i);

						
						tnaziv = c.getString("NAZIV");
						ttext = c.getString("TEXT");
						
						gnaziv = c.getString("GNAZIV");
						gtext = c.getString("GTEXT");
						vnaziv = c.getString("NAZIVV");
						vtext = c.getString("TEXTV");
						rvnaziv = c.getString("NAZIVRV");
						rvtext = c.getString("TEXTRV");
				        result= tnaziv+"\n\n"+ttext+"\n\n"+gnaziv+"\n\n"+gtext+"\n\n"+vnaziv+"\n\n"+vtext+"\n\n"+rvnaziv+"\n\n"+rvtext;
						
					
					}
				}
				else
				{
					LessonsActivity.this.runOnUiThread(new Runnable() {

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
			tvLesson.setText(file_url);

		}

	}
   
	AlertDialog m_Alert;
	 private void selectLesson(){
	    	final CharSequence[] items = {"Uvod","Čitanje","1.Lekcija","2.Lekcija","3.Lekcija","4.Lekcija","5.Lekcija","6.Lekcija","7.Lekcija","8.Lekcija","9.Lekcija","10.Lekcija","11.Lekcija","12.Lekcija","13.Lekcija","14.Lekcija","15.Lekcija","16.Lekcija","17.Lekcija","18.Lekcija","19.Lekcija","20.Lekcija","21.Lekcija","22.Lekcija","23.Lekcija","24.Lekcija","25.Lekcija","26.Lekcija","27.Lekcija","28.Lekcija","29.Lekcija","30.Lekcija","31.Lekcija"};

	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setTitle("Izbor Lekcije");
	    	builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
	    	    public void onClick(DialogInterface dialog, int item) {
	    	  
	    	    
	    	    	switch( item ){
	    	    	case 0:
	    	    		
	    	    		IDTEXT="100";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 1:
	    	    		IDTEXT="101";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 2:
	    	    		IDTEXT="1";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 3:
	    	    		IDTEXT="2";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 4:
	    	    		IDTEXT="3";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 5:
	    	    		IDTEXT="4";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 6:
	    	    		IDTEXT="5";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 7:
	    	    		IDTEXT="6";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 8:
	    	    		IDTEXT="7";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 9:
	    	    		IDTEXT="8";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 10:
	    	    		IDTEXT="9";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 11:
	    	    		IDTEXT="10";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 12:
	    	    		IDTEXT="11";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 13:
	    	    		IDTEXT="12";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 14:
	    	    		IDTEXT="13";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 15:
	    	    		IDTEXT="14";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 16:
	    	    		IDTEXT="15";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 17:
	    	    		IDTEXT="16";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 18:
	    	    		IDTEXT="17";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 19:
	    	    		IDTEXT="18";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 20:
	    	    		IDTEXT="19";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 21:
	    	    		IDTEXT="20";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 22:
	    	    		IDTEXT="21";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 23:
	    	    		IDTEXT="22";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 24:
	    	    		IDTEXT="23";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 25:
	    	    		IDTEXT="24";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 26:
	    	    		IDTEXT="25";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 27:
	    	    		IDTEXT="26";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 28:
	    	    		IDTEXT="27";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 29:
	    	    		IDTEXT="28";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 30:
	    	    		IDTEXT="29";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 31:
	    	    		IDTEXT="30";
	    	    		new LoadAlllessons().execute();
	    	    		break;
	    	    	case 32:
	    	    		IDTEXT="31";
	    	    		new LoadAlllessons().execute();
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
			Button butonLekcije=(Button)findViewById(R.id.optionMenuL);
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
	                	   Intent intent0 = new Intent(LessonsActivity.this,MainActivity.class);
	               		   startActivity(intent0); 
	                	   break;
	                   case 1:
	                	  /* Intent intent = new Intent(LessonsActivity.this,LessonsActivity.class);
	               		   startActivity(intent);  */
	                   case 2:
	                	 
	                	   Intent intent1 = new Intent(LessonsActivity.this,TablesActivity.class);
	               		   startActivity(intent1); 
	               		  break;
	                   case 3:
	                	   Intent intent3 = new Intent(LessonsActivity.this,OthersActivity.class);
	               		   startActivity(intent3); 
	                	   break;
	                   case 4:
	                  	 
	                	   Intent intent4 = new Intent(LessonsActivity.this,TipsActivity.class);
	               		   startActivity(intent4); 
	               		  break;
	                   case 5:
	                    	 
	                	   Intent intent5 = new Intent(LessonsActivity.this,NewsActivity.class);
	               		   startActivity(intent5); 
	               		  break;
	                   case 6:
	                  	 
	                	   Intent intent6 = new Intent(LessonsActivity.this,ContactActivity.class);
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
