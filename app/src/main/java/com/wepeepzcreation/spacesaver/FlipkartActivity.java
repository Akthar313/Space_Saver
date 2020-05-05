package com.wepeepzcreation.spacesaver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class FlipkartActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    ImageView superImageView;
    ProgressBar superProgressBar;
    WebView superWebView;
    private SwipeRefreshLayout swipe;
    private InterstitialAd InterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipkart);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        InterstitialAd = new InterstitialAd(this);
        InterstitialAd.setAdUnitId("ca-app-pub-4595025840440579/2571873934");
        InterstitialAd.loadAd(new AdRequest.Builder().build());

        if (InterstitialAd.isLoaded()) {
            InterstitialAd.show();
        }

        InterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                InterstitialAd.loadAd(new AdRequest.Builder().build());
                LoadWeb();

            }
        });


        superImageView = findViewById(R.id.myImageView);
        superProgressBar = findViewById(R.id.myProgressBar);
        superWebView = findViewById(R.id.myWebView);
        firebaseAuth = FirebaseAuth.getInstance();

        superProgressBar.setMax(100);

        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (InterstitialAd.isLoaded()) {
                    InterstitialAd.show();
                } else {
                    LoadWeb();
                }
            }
        });

        LoadWeb();
    }

    public void LoadWeb() {

        if (InterstitialAd.isLoaded()) {
            InterstitialAd.show();
        } else {

            superWebView = findViewById(R.id.myWebView);
            WebSettings webSettings = superWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            superWebView.loadUrl("https://www.flipkart.com/");
            swipe.setRefreshing(true);
            // code for opening the app
            superWebView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                }


                public void onReceivedError(WebView View, int errorCode, String discription, String failingUrl) {

                    superWebView.loadUrl("file:///android_asset/error.html");

                }

                public void onPageFinished(WebView view, String url) {

                    super.onPageFinished(view, url);
                    //Hide the swipe refresh layout
                    swipe.setRefreshing(false);

                }


            });


            superWebView.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    superProgressBar.setProgress(newProgress);
                }

                @Override
                public void onReceivedIcon(WebView view, Bitmap icon) {
                    super.onReceivedIcon(view, icon);
                    superImageView.setImageBitmap(icon);

                }
            });

            superWebView.setDownloadListener(new DownloadListener() {
                @Override
                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                    Uri myUri = Uri.parse(url);
                    Intent superIntent = new Intent(Intent.ACTION_VIEW);
                    superIntent.setData(myUri);
                    startActivity(superIntent);
                }
            });
        }


    }

    public void webPageOpener(){


    }

    @Override
    public void onBackPressed() {
        if (superWebView.canGoBack()) {
            superWebView.goBack();
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Exit App");
            dialog.setIcon(R.drawable.flipkart);
            dialog.setMessage("Flipkart has nothing to go back, so what next?");
            dialog.setPositiveButton("EXIT ME", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.setCancelable(false);
            dialog.setNegativeButton("STAY HERE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();

        }
    }
}
