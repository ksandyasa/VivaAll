package id.co.viva.pialadunia.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkManager {
	public static boolean isConnectivityAvailable(Context context, boolean show_alert){
		boolean result = false;

		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			result = true;
		} else {
			if(show_alert)
				Toast.makeText(context, "Connection Not Available", Toast.LENGTH_SHORT).show();
		}

		return result;
	}


}
