/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.ionicframework.mo454237;

import android.location.Location;
import android.os.Bundle;

import com.hotelquickly.app.Constants;
import com.hotelquickly.app.util.LocationService;

import org.apache.cordova.*;

import java.util.Locale;

public class HqOfferActivity extends CordovaActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();

        Locale current = getResources().getConfiguration().locale;
        final String language = current.getLanguage();


        if (savedInstanceState != null) {
           appView.restoreState(savedInstanceState);
        } else {


            if(LocationService.isAvaliable(this)) {
                LocationService locationService = new LocationService(getApplicationContext());
                locationService.getLastLocation(new LocationService.LastLocationEvent() {
                    @Override
                    public void onAcquired(Location location) {
                        //launchUrl was Set by <content src="index.html" /> in config.xml
                        loadUrl(Constants.URL.MOBILE_OFFERS
                                + "?" + Constants.Params.LATITUDE + "="
                                + String.valueOf(location.getLatitude())
                                + "&" + Constants.Params.LONGITUDE + "="
                                + String.valueOf(location.getLongitude())
                                + "&" + Constants.Params.LANGUAGE + "="
                                + language);
                    }

                    @Override
                    public void onFailed() {
                        //launchUrl was Set by <content src="index.html" /> in config.xml
                        loadUrl(Constants.URL.MOBILE_OFFERS+ "?" + Constants.Params.LANGUAGE + "="
                                + language);
                    }
                });
            } else {
                //launchUrl was Set by <content src="index.html" /> in config.xml
                loadUrl(Constants.URL.MOBILE_OFFERS+ "?" + Constants.Params.LANGUAGE + "="
                        + language);
            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        appView.saveState(outState);
    }
}
