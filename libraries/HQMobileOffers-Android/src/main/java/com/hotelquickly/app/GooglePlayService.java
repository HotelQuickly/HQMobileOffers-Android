package com.hotelquickly.app;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * GooglePlayService
 * on 2/19/14
 *
 * @author Jetsada Machom <jim@imjim.im>
 */
public class GooglePlayService {

	public static boolean isAvaliable(Context applicationContext) {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(applicationContext) == ConnectionResult.SUCCESS;
	}

}
