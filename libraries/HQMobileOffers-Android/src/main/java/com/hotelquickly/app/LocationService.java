package com.hotelquickly.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import junit.framework.Assert;

/**
 * LocationService
 * on 2/19/14
 *
 * @author Jetsada Machom <jim@imjim.im>
 */
public class LocationService {

	private LocationClient mLocationClient;
	private Context mApplicationContext;

	public LocationService(Context applicationContext) {
		mApplicationContext = applicationContext.getApplicationContext();
	}

	public static boolean isAvaliable(Context applicationContext) {
		if(!GooglePlayService.isAvaliable(applicationContext)) {
			return false;
		}
		LocationManager lm = (LocationManager) applicationContext.getSystemService(Context.LOCATION_SERVICE);
		if(lm == null) {
			Log.d("HQLocationServiceIsAvaliable", "LocationManager is null");
			return false;
		}
		if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(
				LocationManager.NETWORK_PROVIDER)) {
			Log.d("HQLocationServiceIsAvaliable GPS_PROVIDER",
					String.valueOf(lm.isProviderEnabled(LocationManager.GPS_PROVIDER)));
			Log.d("HQLocationServiceIsAvaliable NETWORK_PROVIDER",
					String.valueOf(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)));
			Log.d("HQLocationServiceIsAvaliable PASSIVE_PROVIDER",
					String.valueOf(lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)));
			return true;
		} else {
			Log.d("HQLocationServiceIsAvaliable", "LocationManager provider is not enable");
			return false;
		}
	}

	public void getLastLocation(final LastLocationEvent lastLocationEvent) {
		mLocationClient = new LocationClient(mApplicationContext,
				new GooglePlayServicesClient.ConnectionCallbacks() {
					@Override
					public void onConnected(Bundle bundle) {
						Log.d("GooglePlayServicesClient", "onConnected");
						Location loc = null;
						try {
							loc = mLocationClient.getLastLocation();
						} catch(IllegalStateException e) {
							Log.d("GetLastLocation", "LocationClient", e);
						}
						if(loc != null) {
							lastLocationEvent.onAcquired(loc);
						} else {
							lastLocationEvent.onFailed();
						}
					}

					@Override
					public void onDisconnected() {
						Log.d("GooglePlayServicesClient", "onDisconnected");
					}
				},
				new GooglePlayServicesClient.OnConnectionFailedListener() {
					@Override
					public void onConnectionFailed(ConnectionResult connectionResult) {
						Log.d("GooglePlayServicesClient", "onConnectionFailed");
						Log.d("isGooglePlayServicesAvailable", "No");
						lastLocationEvent.onFailed();
					}
				}
		);
		Assert.assertNotNull(mLocationClient);
		mLocationClient.connect();
	}

	public interface LastLocationEvent {
		public void onAcquired(Location location);

		public void onFailed();
	}

}
