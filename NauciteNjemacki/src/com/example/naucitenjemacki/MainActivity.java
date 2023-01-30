package com.example.naucitenjemacki;

import java.io.BufferedReader;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;





import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;




import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	
    TextView tvNews;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvNews = (TextView)findViewById(R.id.novosti);
		  new Connection().execute();
	    buttonMenu();

	}
	

	 private class Connection extends AsyncTask<String, Void, String>{
		
		 private ProgressDialog dialog;
		 
		  @Override
		    protected void onPreExecute() {
		    	dialog = new ProgressDialog(MainActivity.this);
		        this.dialog.setMessage("Molimo sačekajte...");
		        this.dialog.show();
		    }
		 @Override
	        protected String doInBackground(String... arg0) {
	        	String name = new String();
	        	 try {
			        	
			            org.jsoup.nodes.Document doc =  Jsoup.connect("http://naucitenjemacki.we.bs/index_content.php").get();
			      
			            Elements metaElems = doc.select("div.content");
			            name = metaElems.html();
			         
			          
			        } catch (Exception e) {
			        	 Runnable m_Runnable = new Runnable(){

			    			public void run() {
			    				Toast.makeText(getApplicationContext(),"Greška u konekciji, pokušajte ponovo", 
					                    Toast.LENGTH_SHORT).show();
			    				
			    			}			
			    		};
			        	

			        }
	        	 return name;
	        }
		 public ImageGetter getImageHTML() {
				ImageGetter imageGetter = new ImageGetter() {
					public Drawable getDrawable(String source) {
						try {
							Drawable drawable = Drawable.createFromStream(new URL(source).openStream(), "src name");
							drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
							
							return drawable;
						} catch(IOException exception) {
							Log.v("IOException",exception.getMessage());
							return null;
						}
					}
				};
				return imageGetter;
			}

	        @Override
	        protected void onPostExecute(String text2) {
	        	
		        super.onPostExecute(text2);
		        if (dialog.isShowing()) {
	                dialog.dismiss();
	            }
		        Spanned spannedText = Html.fromHtml(text2,getImageHTML(), null);
		        tvNews.setText(spannedText); 

		    }

	    }
	 


	public void buttonMenu()
	{
		Button butonLekcije=(Button)findViewById(R.id.optionMenu);
		butonLekcije.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			optionMenu();
				
			}
		});
		
	}
	AlertDialog m_Alert;
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
	               	   /*Intent intent0 = new Intent(MainActivity.this,MainActivity.class);
	              		   startActivity(intent0); */
	    	    	
	               	   break;
	                  case 1:
	                	
	               	   Intent intent = new Intent(MainActivity.this,LessonsActivity.class);
	              		   startActivity(intent); 
	              		 break;
	                  case 2:
	                	 
	               	   Intent intent1 = new Intent(MainActivity.this,TablesActivity.class);
	              		   startActivity(intent1); 
	              		  break;
	                  case 3:
	               	   Intent intent3 = new Intent(MainActivity.this,OthersActivity.class);
	              		   startActivity(intent3); 
	               	   break;
	                  case 4:
	                 	 
	               	   Intent intent4 = new Intent(MainActivity.this,TipsActivity.class);
	              		   startActivity(intent4); 
	              		  break;
	                  case 5:
	                   	 
	               	   Intent intent5 = new Intent(MainActivity.this,NewsActivity.class);
	              		   startActivity(intent5); 
	              		  break;
	                  case 6:
	                 	 
	               	   Intent intent6 = new Intent(MainActivity.this,ContactActivity.class);
	              		   startActivity(intent6); 
	              		   
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

}
 

