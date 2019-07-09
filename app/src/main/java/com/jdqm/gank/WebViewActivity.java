package com.jdqm.gank;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity {
    public static final String KEY_URL = "key_url";
    public static final String KEY_TITLE = "key_title";

    private String mUrl;
    private String mTitle;

    private WebView mWebView;
    private TextView mTitleTv;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra(KEY_URL);
        mTitle = intent.getStringExtra(KEY_TITLE);

        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_web_view);

        mWebView = findViewById(R.id.web_view);
        mTitleTv = findViewById(R.id.title_tv);
        mProgressBar = findViewById(R.id.progress_bar);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
                mProgressBar.setProgress(0);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });

        mTitleTv.setText(mTitle);
        mWebView.loadUrl(mUrl);
    }

    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_TITLE, title);
        context.startActivity(intent);
    }
}
