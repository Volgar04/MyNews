package com.nicolappli.mynews.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.nicolappli.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowArticleActivity extends AppCompatActivity {

    @BindView(R.id.web_view_show_article)
    WebView mWebViewShowArticle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_article);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.configureWebView();
    }

    public void configureWebView() {
        String mValue = getIntent().getStringExtra("VALUE_URL_ARTICLE");
        mWebViewShowArticle.loadUrl(mValue);
    }

    public void configureToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
