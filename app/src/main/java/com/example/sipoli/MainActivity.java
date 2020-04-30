package com.example.sipoli;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
//    SwipeRefreshLayout mySwipeRefreshLayout;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.getSupportActionBar().hide();

        webView=findViewById(R.id.webViewLayout);
        webSettings=webView.getSettings();
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccessFromFileURLs(true);

        webView.setWebViewClient(new WebViewClient(){

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog = ProgressDialog.show(MainActivity.this, null,
                        "SIPOLI Akan segera hadir...");
                progressDialog.setCancelable(true);

            }

            public void onPageFinished(WebView view, String url){
                progressDialog.dismiss();
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                progressDialog.dismiss();
                // error page
                Toast.makeText(MainActivity.this,
                        "Gagal Membuka SIPOLI :(", Toast.LENGTH_LONG).show();
            }
        });
        webView.loadUrl("https://vhost.ti.ukdw.ac.id/~nikolaus/");

    }
}
