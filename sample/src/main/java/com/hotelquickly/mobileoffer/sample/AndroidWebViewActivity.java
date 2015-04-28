package com.hotelquickly.mobileoffer.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import com.hotelquickly.app.HQMobileOffersLoader;

import junit.framework.Assert;


/**
 * AndroidWebViewActivity
 * on 4/27/15.
 *
 * @author Jutikorn Varojananulux <jutikorn.v@gmail.com>
 */
public class AndroidWebViewActivity extends ActionBarActivity {

    public static final String HIDE_TOOLBAR = "HideToolBar";
    private HQMobileOffersLoader mOfferLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        boolean hideToolBar = getIntent().getBooleanExtra(HIDE_TOOLBAR, true);
        ActionBar actionbar = getSupportActionBar();

        if(hideToolBar && actionbar!= null){
            actionbar.hide();
        }
        WebView webView = (WebView) findViewById(R.id.webview);

        Assert.assertNotNull(webView);

        mOfferLoader = new HQMobileOffersLoader(this, webView);

        if (savedInstanceState != null) {
            mOfferLoader.restoreState(savedInstanceState);
        }else {
            mOfferLoader.load();
        }
    }



    @Override
    public void onBackPressed() {

        if(mOfferLoader.canGoBack()){
            mOfferLoader.goBack();
        }else{
            super.onBackPressed();
        }

    }
}
