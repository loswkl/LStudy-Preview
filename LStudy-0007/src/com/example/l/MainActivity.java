package com.example.l;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.support.v4.app.NavUtils;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;


@SuppressLint("ParserError") public class MainActivity extends Activity {

	public static final int SHOW_RESPONSE = 0;
	 	WebView wv;
	    Button btnButton;
	    TextView t_s,t_fh,t_tc,t_cd;
	    ImageView i_fh,i_sy,i_cd,i_sz;
	    int ail=0;
	    String strbb;
	    String lsv="0.0.0.0.0007";
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    
	    	
	    //检测更新-支线获取
		  	new Thread(new Runnable() {
			    
	            public void run() {
			try  
			{  
			URL url = new URL("http://a.loswkl.xyz/lapp/lapp");  
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);  
			//conn.setDefaultUseCaches(false);//禁用缓存
			conn.setConnectTimeout(10000);  
			conn.setRequestMethod("GET");  
			conn.setRequestProperty("accept", "*/*");  
			String location = conn.getRequestProperty("location");  
			int resCode = conn.getResponseCode();  
			conn.connect();  
			InputStream stream = conn.getInputStream();  
			byte[] data=new byte[102400];  
			int length=stream.read(data);  
			String str=new String(data,0,length); 
			//在子线程中将Message对象发出去
			Message message = new Message();
		    message.obj = str.toString();
	        handler.sendMessage(message);
			conn.disconnect();  
			System.out.println(str);  
			stream.close();  
			}  
			catch(Exception ee)  
			{  
			//System.out.print("ee:"+ee.getMessage());   
			}      
	        }
	     }).start();//这个start()方法不要忘记了        
	    	
	    	//欢迎界面
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	       //跳转HI
	        Intent i=new Intent();
	    	i.setClass(MainActivity.this, hi.class);//本页面和跳转页面
	    	startActivity(i);

//	        t_fh=(TextView)findViewById(R.id.textView2);
//	        t_tc=(TextView)findViewById(R.id.textView3);
//	        t_cd=(TextView)findViewById(R.id.textView4);
	        i_fh=(ImageView)findViewById(R.id.imageView1);
	        i_sy=(ImageView)findViewById(R.id.imageView2);
	        i_cd=(ImageView)findViewById(R.id.imageView3);
	        i_sz=(ImageView)findViewById(R.id.imageView4);
	        
	        
	        
	        
	        wv = (WebView)findViewById(R.id.webView1);
	        wv.getSettings().setJavaScriptEnabled(true);
	        wv.setScrollBarStyle(0);
	        // 启用支持javascript 
	        WebSettings settings = wv.getSettings(); 
	        settings.setJavaScriptEnabled(true); 
		 
	        wv.loadUrl("http://a.loswkl.xyz");

	        wv.setWebViewClient(new WebViewClient()
	        {
	        	public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
	  			  
	        		loadurl(view,url);
	        		return true;  
	        	}
	        });


	        
	        
	        //主页
	        i_sy.setOnClickListener(new View.OnClickListener() {
			
	  		  public void onClick(View arg0) {
	  			  // TODO Auto-generated method stub
	  			wv.loadUrl("http://a.loswkl.xyz");
	  			Toast.makeText(getApplicationContext(), "正在加载L'Study", Toast.LENGTH_SHORT).show();
	  		  }
	  	  });
	        
 
	        
	  	  //返回
	  	  i_fh.setOnClickListener(new View.OnClickListener() {
			
	  		  	public void onClick(View arg0) {
	  			  // TODO Auto-generated method stub
	  		  	wv.goBack(); 
	  		  Toast.makeText(getApplicationContext(), "返回上一页", Toast.LENGTH_SHORT).show();
				
	  		  }
	  	  });
	  	  //设置
	  	  i_sz.setOnClickListener(new View.OnClickListener() {
			
	  		  public void onClick(View arg0) {
	  			  // TODO Auto-generated method stub
	  			openOptionsMenu();  
	  			
				
	  		  }
	  	  });
	  	  
	    
	  	  //菜单
	  	i_cd.setOnClickListener(new View.OnClickListener() {
			
	  		 public void onClick(View arg0) {
	  			  // TODO Auto-generated method stub
//	  			String str="欢迎回来，我的朋友！\n我们正在积极努力的为本程序增加更多功能，也感谢您的安装和关注！ \n版本：0.0.0.0.0005";
////	  			getIntentData ac=new getIntentData();
////	  			str=ac.getIntentData("http://a.loswkl.xyz/lapp/tz");
////              软件崩溃
//	  			
//	  			AlertDialog.Builder b= new AlertDialog.Builder(MainActivity.this);
//	  			b.setTitle("关于");
//	  			b.setMessage(str);
//
//				b.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//					
//					public void onClick(DialogInterface arg0, int arg1) {
//						// TODO Auto-generated method stub
//						
//					}
//				});
//				b.show();
			
	  			Intent i=new Intent();
		    	i.setClass(MainActivity.this, Cd.class);//本页面和跳转页面
		    	startActivity(i);
				
	  		 
			
	  			
				
	  		  }
	  	  });
	  	  
	    

	  	
	    	
	  	
	    }
	    
	    //其他代码
	    
	    
	    
	    
	    
	    
	    
	    
	    
	 // 改写物理按键 返回键的逻辑 
	    @Override
		  public boolean onKeyDown(int keyCode, KeyEvent event) { 
		  // TODO Auto-generated method stub 
		  if (keyCode == KeyEvent.KEYCODE_BACK) { 
		  if (wv.canGoBack()) { 
		  // 返回上一页面 
		  wv.goBack(); 
		  Toast.makeText(getApplicationContext(), "返回上一页", Toast.LENGTH_SHORT).show();
		  return true; 
		  } else { 
		  // 退出程序 
		  finish(); 
		  } 
		  } 
		  return super.onKeyDown(keyCode, event); 
		  } 
	    
	    
	    
	    
	    public void loadurl(final WebView view,final String url){
	    	new Thread(){
	    		public void run(){
	    			view.loadUrl(url);
	    
	    		}
	    	}.start();
	    }
	    
	    
	    
	    
	    //系统菜单
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    // TODO Auto-generated method stub
	    	
	    if(item.getItemId()==1){
	    	wv.loadUrl("http://a.loswkl.xyz");
	    }
	    else if(item.getItemId()==2){
	    	wv.goBack(); 
	    }
	    else if(item.getItemId()==3){
	    	finish(); 
	    }
	    else if(item.getItemId()==4){
	    	
	   //判断是否有新版本
	    		if(strbb.equals(lsv)){
    				Toast.makeText(getApplicationContext(), "软件已为最新版", Toast.LENGTH_SHORT).show();
      	
    			}else{
      		
    				AlertDialog.Builder b= new AlertDialog.Builder(MainActivity.this);
    				b.setTitle("更新");
    				b.setMessage("已发现最新版："+strbb);
    		
    				
    				b.setPositiveButton("确认", new DialogInterface.OnClickListener() {
    			
    					public void onClick(DialogInterface arg0, int arg1) {
    						// TODO Auto-generated method stub
    						Intent intent = new Intent();        
    						intent.setAction("android.intent.action.VIEW");    
    						Toast.makeText(getApplicationContext(), "我们将跳转到下载页面", Toast.LENGTH_SHORT).show();
    						Uri content_url = Uri.parse("https://a.loswkl.xyz/archives/132");    //http://a.loswkl.xyz/lapp/LStudy.ap
    						intent.setData(content_url);  
    						startActivity(intent);
    						
    						
    					}
    				});
    				b.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    					
    					public void onClick(DialogInterface arg0, int arg1) {
    						// TODO Auto-generated method stub
    				
    					}
    				});
    				b.show();
    			}
	    	
	    	
	    	

         
         
	    	

  
	    }
	    
	    return super.onOptionsItemSelected(item);
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        
	        
	       // menu.add(0,1,0,"L'Study");
	        //menu.add(0,2,0,"返回");
	        
	        menu.add(0,4,0,"检查更新");
	        menu.add(0,3,0,"退出");
	        
	        return true;
	    }
	    
	    //检测更新-获取支线信息
	     private Handler handler = new Handler() {
	    	 
	         @Override
	        public void handleMessage(Message msg) {
	            super.handleMessage(msg);
	             switch (msg.what) {
	             case SHOW_RESPONSE:
	                 String response = (String) msg.obj;
	                 strbb=response;
	                 break;
	 
	             default:
	                 break;
	             }            
	         }

	     };

	  

}

