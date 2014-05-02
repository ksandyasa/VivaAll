package id.co.viva.pialadunia.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class FetchContent {
	private String url;
	private boolean success;
	private int status_code = 0;
	private String error_message = "";
	private String response_body = "";
	private byte[] response_byte = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatusCode() {
		return status_code;
	}

	public void setStatusCode(int status_code) {
		this.status_code = status_code;
	}

	public String getErrorMessage() {
		return error_message;
	}

	public void setErrorMessage(String error_message) {
		this.error_message = error_message;
	}

	public String getResponseBody() {
		return response_body;
	}

	public void setResponseBody(String response_body) {
		this.response_body = response_body;
	}

	public byte[] getResponseByte() {
		return response_byte;
	}

	public void setResponseByte(byte[] response_byte) {
		this.response_byte = response_byte;
	}

	public void fetchURL() {
		fetchURL(true);
	}

	public void fetchURL(boolean is_text){
		success = false;

		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
		HttpConnectionParams.setSoTimeout(httpParams, 30000);

		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			error_message = "ClientProtocolException:" + e.getMessage();
		} catch (ConnectTimeoutException e) {
			error_message = "ConnectTimeoutException:" + e.getMessage();
		} catch (IOException e) {
			error_message = "IOException:" + e.getMessage();
		}

		if(httpResponse != null){
			StatusLine statusLine = httpResponse.getStatusLine();
			status_code = statusLine.getStatusCode();
			if (status_code == HttpStatus.SC_OK) {
				try {
					if(is_text){
						response_body = EntityUtils.toString(httpResponse.getEntity());
					}else{
						response_byte = EntityUtils.toByteArray(httpResponse.getEntity());
					}
					success = true;
				} catch (ParseException e) {
					error_message = "ParseException:" + e.getMessage();
				} catch (IOException e) {
					error_message = "IOException:" + e.getMessage();
				}
			} else {
				error_message = "HTTP Status " + status_code;
			}
		}
	}
}