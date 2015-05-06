package com.hotelquickly.app;

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
import com.hotelquickly.app.util.LocationService;

import java.util.Locale;


/**
 * HQMobileOffersLoader
 * on  4/28/15.
 *
 * @author Jutikorn Varojananulux <jutikorn.v@gmail.com>
 */
public class HQMobileOffersLoader {

    private WebView mWebView;
    private Context mContext;
    private String mApiKey;

    public HQMobileOffersLoader(Context context, WebView webView, String apiKey){
        mContext = context;
        mWebView = webView;
        mApiKey = apiKey;
        setUp();
    }

    private void setUp() {

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
            String databasePath = mContext.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
            mWebView.getSettings().setDatabasePath(databasePath);
        }
    }

    public void load(){
        final String language = getCurrentLangCode();
        if(LocationService.isAvaliable(mContext)) {
            LocationService locationService = new LocationService(mContext);
            locationService.getLastLocation(new LocationService.LastLocationEvent() {
                @Override
                public void onAcquired(Location location) {
                    mWebView.loadUrl(Constants.URL.MOBILE_OFFERS
                            + "?" + Constants.Params.LATITUDE + "="
                            + String.valueOf(location.getLatitude())
                            + "&" + Constants.Params.LONGITUDE + "="
                            + String.valueOf(location.getLongitude())
                            + "&" + Constants.Params.LANGUAGE + "="
                            + language
                            + "&" + Constants.Params.API_KEY + "="
                            + mApiKey);
                }

                @Override
                public void onFailed() {
                    mWebView.loadUrl(Constants.URL.MOBILE_OFFERS+ "?" + Constants.Params.LANGUAGE + "="
                            + language
                            + "&" + Constants.Params.API_KEY + "="
                                    + mApiKey);
                }
            });
        } else {
            mWebView.loadUrl(Constants.URL.MOBILE_OFFERS
                    + "?" + Constants.Params.LANGUAGE + "="
                    + language
                    +  "&" + Constants.Params.API_KEY + "="
                    + mApiKey);
        }
    }

    public void restoreState(Bundle savedInstanceState){
        mWebView.restoreState(savedInstanceState);
    }

    private String getCurrentLangCode(){
        Locale current = mContext.getResources().getConfiguration().locale;
        return current.getLanguage();
    }

    public boolean canGoBack() {
        return mWebView.canGoBack();
    }

    public void goBack() {
        mWebView.goBack();
    }

}
