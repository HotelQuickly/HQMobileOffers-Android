package com.hotelquickly.mobileoffer.sample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ToggleButton;


import junit.framework.Assert;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{


    private CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button androidWebViewBtn = (Button) findViewById(R.id.webviewbutton);
        Assert.assertNotNull(androidWebViewBtn);
        androidWebViewBtn.setOnClickListener(this);

        mCheckBox = (CheckBox) findViewById(R.id.toggleButton);
        Assert.assertNotNull(mCheckBox);
    }

    @Override
    public void onClick(View v) {
         if(v.getId() == R.id.webviewbutton){
            Intent intent = new Intent(this, AndroidWebViewActivity.class );
             intent.putExtra(AndroidWebViewActivity.HIDE_TOOLBAR, mCheckBox.isChecked());
            startActivity(intent);
        }
    }
}
