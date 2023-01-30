package com.example.naucitenjemacki;

import java.io.IOException;

import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;





import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class NewsActivity extends Activity{

	
    TextView tvNews;
    
    ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
	
      
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
         
		tvNews = (TextView)findViewById(R.id.news);
		
		 listView.setAdapter(adapter); 
         
         listView.setOnItemClickListener(new OnItemClickListener() {

               public void onItemClick(AdapterView<?> parent, View view,
                  int position, long id) {
                
                int itemPosition     = position;
                

                switch(itemPosition)
                {
                
                case 0: 
              	   Intent intent0 = new Intent(NewsActivity.this,MainActivity.class);
             		   startActivity(intent0); 
              	   break;
                 case 1:
              	   Intent intent = new Intent(NewsActivity.this,LessonsActivity.class);
             		   startActivity(intent);  
                 case 2:
              	 
              	   Intent intent1 = new Intent(NewsActivity.this,TablesActivity.class);
             		   startActivity(intent1); 
             		  break;
                 case 3:
              	   Intent intent3 = new Intent(NewsActivity.this,OthersActivity.class);
             		   startActivity(intent3); 
              	   break;
                 case 4:
                	 
              	   Intent intent4 = new Intent(NewsActivity.this,TipsActivity.class);
             		   startActivity(intent4); 
             		  break;
                 case 5:
                  	 
              	   Intent intent5 = new Intent(NewsActivity.this,NewsActivity.class);
             		   startActivity(intent5); 
             		  break;
                 case 6:
                	 
              	   Intent intent6 = new Intent(NewsActivity.this,ContactActivity.class);
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
	            org.jsoup.nodes.Document doc =  Jsoup.connect("http://naucitenjemacki.we.bs/news_m.php").get();
	         
	            Elements metaElems = doc.select("article.underline");
	            name = metaElems.html();
	            Spanned spannedText = Html.fromHtml(name,getImageHTML(), null);
				tvNews.setText(spannedText);

	        } catch (Exception e) {

	        	Toast.makeText(getApplicationContext(),"Greška u konekciji, pokušajte ponovo", 
	                    Toast.LENGTH_SHORT).show();

	        }

	}
	public ImageGetter getImageHTML() {
		ImageGetter imageGetter = new ImageGetter() {
			public Drawable getDrawable(String source) {
				try {
					Drawable drawable = Drawable.createFromStream(new URL(source).openStream(), "src name");
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
					return drawable;
				} catch(IOException exception) {
					Log.v("IOException",exception.getMessage());
					return null;
				}
			}
		};
		return imageGetter;
	}

}
