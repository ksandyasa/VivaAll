package id.co.viva.pialadunia.activity;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.share.Base;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		Base.setTypeFace(getAssets());

		super.onPostCreate(savedInstanceState);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		new Handler().postDelayed(
				new Runnable() {
					public void run() {
						goToMainActivity();
					}
				}, 1000);
	}

	@Override
	public void onBackPressed(){
		//do nothing
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	}

	private void goToMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

		finish();
	}
}
