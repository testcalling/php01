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

@SuppressLint("NewApi")
public class ContactActivity extends Activity{

	
    TextView tvTips;
    
    ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
	
      
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
             	   Intent intent0 = new Intent(ContactActivity.this,MainActivity.class);
            		   startActivity(intent0); 
             	   break;
                case 1:
             	   Intent intent = new Intent(ContactActivity.this,LessonsActivity.class);
            		   startActivity(intent);  
                case 2:
             	 
             	   Intent intent1 = new Intent(ContactActivity.this,TablesActivity.class);
            		   startActivity(intent1); 
            		  break;
                case 3:
             	   Intent intent3 = new Intent(ContactActivity.this,OthersActivity.class);
        		   startActivity(intent3); 
             	   break;
                case 4:
               	 
             	   Intent intent4 = new Intent(ContactActivity.this,TipsActivity.class);
            		   startActivity(intent4); 
            		  break;
                case 5:
                 	 
             	   Intent intent5 = new Intent(ContactActivity.this,NewsActivity.class);
            		   startActivity(intent5); 
            		  break;
                case 6:
               	 
             	/*   Intent intent6 = new Intent(ContactActivity.this,ContactActivity.class);
            		   startActivity(intent6); */
            		   
            		  break;
            	   default:
            		   break;
                }
              
               }

          }); 
        
	  networkAsynch();
	 
	}
	public void networkAsynch()
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	
	

}
