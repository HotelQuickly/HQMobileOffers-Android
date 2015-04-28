package com.hotelquickly.app.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hotelquickly.app.Constants;
import com.hotelquickly.app.LocationService;

import junit.framework.Assert;

import org.apache.cordova.R;

import java.util.Locale;

/**
 * Created by hqadmin on 4/27/15.
 */

/**
 * AndroidWebViewActivity
 * on 4/27/15.
 *
 * @author Jutikorn Varojananulux <jutikorn.v@gmail.com>
 */
public class AndroidWebViewActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView) findViewById(R.id.webview);

        Assert.assertNotNull(mWebView);

        initWebView();

        Locale current = getResources().getConfiguration().locale;
        final String language = current.getLanguage();

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        }else {
            if(LocationService.isAvaliable(this)) {
                LocationService locationService = new LocationService(getApplicationContext());
                locationService.getLastLocation(new LocationService.LastLocationEvent() {
                    @Override
                    public void onAcquired(Location location) {
                        mWebView.loadUrl(Constants.URL.MOBILE_OFFERS
                                + "?" + Constants.Params.LATITUDE + "="
                                + String.valueOf(location.getLatitude())
                                + "&" + Constants.Params.LONGITUDE + "="
                                + String.valueOf(location.getLongitude())
                                + "&" + Constants.Params.LANGUAGE + "="
                                + language);
                    }

                    @Override
                    public void onFailed() {
                        mWebView.loadUrl(Constants.URL.MOBILE_OFFERS+ "?" + Constants.Params.LANGUAGE + "="
                                + language);
                    }
                });
            } else {
                mWebView.loadUrl(Constants.URL.MOBILE_OFFERS
                        + "?" + Constants.Params.LANGUAGE + "="
                        + language);
            }
        }
    }

    private void initWebView() {
        mWebView.setLongClickable(false);
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.setBackgroundColor(0x00000000);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("MobileOffers", cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId());
                return true;
            }
        });

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
            mWebView.getSettings().setDatabasePath(databasePath);
        }
    }

    @Override
    public void onBackPressed() {

        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            super.onBackPressed();
        }

    }
}
