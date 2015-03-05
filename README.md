# HQMobileOffers-Android
The *HotelQuickly* Cordova wrapper for third party integration of mobile/webview based hotel offers

##Integration into an existing iOS project

1) Download the project`HQMobileOffers-Android`

2) Put it into your 'libraries' directory 

3) If you are using Eclipse, just import this to your workspace and make it as your library project.

4) If you are using Android Studio, include it in to your project in 'setting.gradle' file.

```
include ':libraries:FloatingButton'
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

```
<activity android:name="com.ionicframework.mo454237.HqOfferActivity" />
```

To open the mobile just do a simple intent

```
Intent intent = new Intent(this,HqOfferActivity.class );
intent.startActivity();
```


