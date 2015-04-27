package com.hotelquickly.mobileoffer.sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hotelquickly.app.ui.activity.AndroidWebViewActivity;
import com.ionicframework.mo454237.HqOfferActivity;

import junit.framework.Assert;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cordovaBtn = (Button) findViewById(R.id.cordovabutton);
        Assert.assertNotNull(cordovaBtn);
        cordovaBtn.setOnClickListener(this);

        Button androidWebViewBtn = (Button) findViewById(R.id.webviewbutton);
        Assert.assertNotNull(androidWebViewBtn);
        androidWebViewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cordovabutton){
            Intent intent = new Intent(this,HqOfferActivity.class );
            startActivity(intent);
        }else if(v.getId() == R.id.webviewbutton){
            Intent intent = new Intent(this, AndroidWebViewActivity.class );
            startActivity(intent);
        }
    }
}
