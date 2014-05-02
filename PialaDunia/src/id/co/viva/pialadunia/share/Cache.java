package id.co.viva.pialadunia.share;

public class Cache {
	private static String registrationId;

	public static String getRegistrationId() {
		return registrationId;
	}

	public static void setRegistrationId(String registrationId) {
		Cache.registrationId = registrationId;
	}
}