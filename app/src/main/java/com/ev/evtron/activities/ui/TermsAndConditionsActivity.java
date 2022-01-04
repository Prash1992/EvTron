package com.ev.evtron.activities.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.ev.evtron.R;

public class TermsAndConditionsActivity extends AppCompatActivity {
private WebView wvTermsOfService;
private ImageView ivBackTerms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);
        initialisation();
        setDefaults();
        setListeners();
    }

    private void setListeners() {
        ivBackTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setDefaults() {
        wvTermsOfService.setWebViewClient(new MyBrowser());
        String url = "http://103.212.120.220/evtronWebView/termsAndCons.html";

        wvTermsOfService.getSettings().setLoadsImagesAutomatically(true);
        wvTermsOfService.getSettings().setJavaScriptEnabled(true);
        wvTermsOfService.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvTermsOfService.loadUrl(url);
    }

    private void initialisation() {
        wvTermsOfService = findViewById(R.id.wvTermsOfService);
        ivBackTerms = findViewById(R.id.ivBackTerms);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}