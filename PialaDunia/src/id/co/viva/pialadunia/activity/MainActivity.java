package id.co.viva.pialadunia.activity;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.fragment.BlankFragment;
import id.co.viva.pialadunia.fragment.FrontFragment;
import id.co.viva.pialadunia.fragment.MenuFragment;
import id.co.viva.pialadunia.fragment.WidgetNewsGridFragment;
import id.co.viva.pialadunia.share.Base;
import id.co.viva.pialadunia.share.Cache;
import id.co.viva.pialadunia.util.FetchContent;
import id.co.viva.pialadunia.util.Util;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends SlidingFragmentActivity {
	private GoogleCloudMessaging gcm;
	private static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "app_version";
	private static final String PROJECT_NUMBER = "968305046465"; //398860040547
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private static long back_pressed;

	private int menu_position = 0;
	private ActionBar ab;
	private SlidingMenu sm;
	private MenuFragment fragmentMenu = new MenuFragment();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
		ImageLoader.getInstance().init(config);
		
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.activity_menu);
		setSlidingActionBarEnabled(false);

		// show home as up so we can toggle
		ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(false);
		ab.setDisplayShowHomeEnabled(false);
		ab.setDisplayShowCustomEnabled(true);
		ab.setCustomView(R.layout.action_bar_main);

		// customize the SlidingMenu
		sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		//sm.setBehindWidthRes(R.dimen.slidingmenu_width);
		//sm.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		//sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);
		//sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		//sm.setMode(SlidingMenu.LEFT);
		sm.setSlidingEnabled(false);
		//sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, fragmentMenu).commit();

		switchContent(menu_position);

		if (checkPlayServices()) {
			Cache.setRegistrationId(getRegistrationId(getApplicationContext()));

			gcm = GoogleCloudMessaging.getInstance(getApplicationContext());

			if (Cache.getRegistrationId().equals("")) {
				registerInBackground();
			}
		}
	}

	@Override
	public void onBackPressed() {
		if(menu_position!=0) {
			switchContent(0);
		} else {
			if (back_pressed + 2000 > System.currentTimeMillis()) {
				super.onBackPressed();
			} else {
				Toast.makeText(getBaseContext(), "Press back key again to exit!", Toast.LENGTH_SHORT).show();
			}
			back_pressed = System.currentTimeMillis();
		}
	}

	public void toggleSlidingMenu(View view) {
		toggle();
	}

	public void switchContent(int position) {
		menu_position = position;

		Fragment fragmentContent;
		switch(position) {
		case 0:
			fragmentContent = new FrontFragment();
			break;
		case 1:
			fragmentContent = new WidgetNewsGridFragment();
			break;
		default:
			fragmentContent = new BlankFragment();
			Bundle args = new Bundle();
			args.putString("text", Base.getMenu().get(position).getDescription());
			fragmentContent.setArguments(args);
		}

		getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragmentContent).commit();


		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
				fragmentMenu.setSelected(menu_position);
			}
		}, 50);
	}

	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				finish();
			}
			return false;
		}
		return true;
	}

	private String getRegistrationId(Context context) {
		final SharedPreferences prefs = getSharedPreferences(this.getClass().getSimpleName(), Context.MODE_PRIVATE);
		String reg_id = prefs.getString(PROPERTY_REG_ID, "");
		if (reg_id.equals("")) {
			return "";
		}

		// Check if app was updated; if so, it must clear the registration ID
		// since the existing regID is not guaranteed to work with the new
		// app version.
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = Util.getAppVersion(context);
		if (registeredVersion != currentVersion) {
			return "";
		}

		return reg_id;
	}

	private void storeRegistrationId(Context context, String regId) {
		final SharedPreferences prefs = getSharedPreferences(this.getClass().getSimpleName(), Context.MODE_PRIVATE);
		int appVersion = Util.getAppVersion(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(PROPERTY_REG_ID, regId);
		editor.putInt(PROPERTY_APP_VERSION, appVersion);
		editor.commit();
	}

	private void registerInBackground() {
		new AsyncTask<String, String, String>() {
			@Override
			protected String doInBackground(String... params) {
				String msg = "";
				try {
					if(gcm==null) {
						gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
					}
					Cache.setRegistrationId(gcm.register(PROJECT_NUMBER));
					msg = "Device registered, registration ID=" + Cache.getRegistrationId();

					// You should send the registration ID to your server over HTTP,
					// so it can use GCM/HTTP or CCS to send messages to your app.
					// The request to your server should be authenticated if your app
					// is using accounts.
					if(sendRegistrationIdToBackend()) {
						// Persist the regID - no need to register again.
						storeRegistrationId(getApplicationContext(), Cache.getRegistrationId());
					}
				} catch(IOException e) {
					msg = "Error: " + e.getMessage();
				}
				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {}

		}.execute(null, null, null);
	}

	private boolean sendRegistrationIdToBackend() {
		boolean result = false;

		FetchContent fetchContent = new FetchContent();
		fetchContent.setUrl(getResources().getString(R.string.url_gcm_regiter) + "?sender_id=" + Cache.getRegistrationId());
		fetchContent.fetchURL();

		try {
			JSONObject jObject = new JSONObject(fetchContent.getResponseBody());
			String status = jObject.getString("status");
			if(status.equals("1")) result = true;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}
}