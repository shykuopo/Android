package com.example.win81.webtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        wv = (WebView)findViewById(R.id.wv);
        pb = (ProgressBar)findViewById(R.id.pb);
        wv.getSettings().setJavaScriptEnabled(true);//起用javaScript
        wv.getSettings().setBuiltInZoomControls(true);//起用縮放功能
        wv.invokeZoomPicker();//起用縮放工具

        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view , int progress){
                pb.setProgress(progress);
                pb.setVisibility(progress<100? View.VISIBLE:View.GONE);

            }
        });
        wv.loadUrl("https://litotom.com/2016/05/15/android-json-okhttp/");
    }

    @Override
    public void onBackPressed(){
        if(wv.canGoBack()){
            wv.goBack();
            return;
        }

        super.onBackPressed();
    }
}
