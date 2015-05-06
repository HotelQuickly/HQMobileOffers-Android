# HQMobileOffers-Android
The *HotelQuickly* Cordova wrapper for third party integration of mobile/webview based hotel offers

##Integration into an existing iOS project

1) Download the project`HQMobileOffers-Android`

2) Put it into your 'libraries' directory 

3) If you are using Eclipse, just import this to your workspace and make it as your library project.

4) If you are using Android Studio, include it in to your project in 'setting.gradle' file.

```
include ':libraries:HQMobileOffers-Android'
```
add a project in your dependency

```
dependencies {
    compile project(':libraries:HQMobileOffers-Android')
    }
```

#Usage
---
To use this, make sure that your project has internet permission.
```
<uses-permission android:name="android.permission.INTERNET"/>
```

Declar HqOfferActivity in your AndroidManifest file. 
*Please know that we only support the MobileOffers in portrait orientation. So keep it be in portrait.

```
<activity android:name="com.ionicframework.mo454237.HqOfferActivity"
          android:screenOrientation="portrait"/>
```

To open the HQMobileOffers you an simply create an Activity

which have a webview component inside.
```
<?xml version="1.0" encoding="utf-8"?>
<WebView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/webview"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

</WebView>
```

And simply bind it
```
WebView webView = (WebView) findViewById(R.id.webview);
```


Then you can call HQMobileOffersLoader to auto render the mobile Offer by passing the context, webview and your api key

```
offerLoader = new HQMobileOffersLoader(this, webView, API_KEY);
offerLoader.load();
```

To handle the canGoBack(), goBack() and restoreState() webview methods you can just call OfferLoader.canGoBack() instead.

```
 @Override
    public void onBackPressed() {

        if(mOfferLoader.canGoBack()){
            mOfferLoader.goBack();
        }else{
            super.onBackPressed();
        }
    }
```

```
if (savedInstanceState != null) {
            mOfferLoader.restoreState(savedInstanceState);
        }else {
            mOfferLoader.load();
        }
```