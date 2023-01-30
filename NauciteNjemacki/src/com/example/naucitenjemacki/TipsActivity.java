package com.example.naucitenjemacki;

import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.select.Elements;





import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

@SuppressLint("NewApi")
public class TipsActivity extends Activity{

	
    TextView tvTips;
    
    ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips);
	
      
		 listView = (ListView) findViewById(R.id.list);
         

         String[] values = new String[] { "Početna",
        		                          "Lekcije",
        		                          "Tabele",
        		                          "Ostalo",
        		                          "Savjeti",
        		                          "Novosti",
        		                          "Kontakt"
                                         };
         
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_list_item_1, android.R.id.text1, values);
         
		tvTips = (TextView)findViewById(R.id.tips);
		
		 listView.setAdapter(adapter); 
         
	
	
      
         listView.setOnItemClickListener(new OnItemClickListener() {

               public void onItemClick(AdapterView<?> parent, View view,
                  int position, long id) {
                
                int itemPosition     = position;
                

                switch(itemPosition)
                {
                
                case 0: 
               	   Intent intent0 = new Intent(TipsActivity.this,MainActivity.class);
              		   startActivity(intent0); 
               	   break;
                  case 1:
               	   Intent intent = new Intent(TipsActivity.this,LessonsActivity.class);
              		   startActivity(intent);  
                  case 2:
               	 
               	   Intent intent1 = new Intent(TipsActivity.this,TablesActivity.class);
              		   startActivity(intent1); 
              		  break;
                  case 3:
               	   Intent intent3 = new Intent(TipsActivity.this,OthersActivity.class);
              		   startActivity(intent3); 
               	   break;
                  case 4:
                 	 
               	  /* Intent intent4 = new Intent(TipsActivity.this,TipsActivity.class);
              		   startActivity(intent4); */
              		  break;
                  case 5:
                   	 
               	   Intent intent5 = new Intent(TipsActivity.this,NewsActivity.class);
              		   startActivity(intent5); 
              		  break;
                  case 6:
                 	 
               	   Intent intent6 = new Intent(TipsActivity.this,ContactActivity.class);
              		   startActivity(intent6); 
              		   
              		  break;
              	   default:
              		   break;
                }
              
               }

          }); 
        
	  networkAsynch();
	  selectURL();
	}
	public void networkAsynch()
	{
		int SDK_INT = android.os.Build.VERSION.SDK_INT;

		 if (SDK_INT>8){

		  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		  StrictMode.setThreadPolicy(policy);
		 }
	}

	public void selectURL()
	{
		  
	       
			try {
				String name;
	            org.jsoup.nodes.Document doc =  Jsoup.connect("http://naucitenjemacki.we.bs/savjeti_m.php").get();
	         
	            Elements metaElems = doc.select("article.underline");
	            name = metaElems.html();

				tvTips.setText(Html.fromHtml(name));

	        } catch (Exception e) {

	        	
	        	Toast.makeText(getApplicationContext(),"Greška u konekciji, pokušajte ponovo", 
	                    Toast.LENGTH_SHORT).show();
	        }
			
			
	}
	
	 



}
