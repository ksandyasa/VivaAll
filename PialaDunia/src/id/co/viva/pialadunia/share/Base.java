package id.co.viva.pialadunia.share;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.object.Menu;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class Base {
	private final static ArrayList<Menu> menu = new ArrayList<Menu>(
			Arrays.asList(
					new Menu("home", "Home", R.drawable.ic_blank, false),
					new Menu("news", "News", R.drawable.ic_news, false),
					new Menu("worldcuptainment", "Worldcuptainment", R.drawable.ic_worldcuptainment, false),
					new Menu("sejarah", "Sejarah", R.drawable.ic_sejarah, false),
					new Menu("peta_stadion", "Peta Stadion", R.drawable.ic_peta, false),
					new Menu("live_score", "Live Score", R.drawable.ic_livescore, false),
					new Menu("klasemen", "Klasemen", R.drawable.ic_klasmen, false),
					new Menu("statistik", "Statistik", R.drawable.ic_statistik, false),
					new Menu("jadwal", "Jadwal", R.drawable.ic_jadwal, false),
					new Menu("setting", "Setting", R.drawable.ic_setting, false)
					)
			);

	private static Typeface typeFaceElectrolizeRegular;
	private static Typeface typeFaceMichroma;
	private static Typeface typeFaceRobotoLight;
	private static Typeface typeFaceRobotoBold;
	
	public static ArrayList<Menu> getMenu() {
		return menu;
	}

	public static void setTypeFace(AssetManager am) {
		Base.typeFaceElectrolizeRegular = Typeface.createFromAsset(am, "fonts/Electrolize-Regular.ttf");
		Base.typeFaceMichroma = Typeface.createFromAsset(am, "fonts/Michroma.ttf");
		Base.typeFaceRobotoLight = Typeface.createFromAsset(am, "fonts/Roboto/Roboto-Light.ttf");
		Base.typeFaceRobotoBold = Typeface.createFromAsset(am, "fonts/Roboto/Roboto-Bold.ttf");
	}

	public static Typeface getTypeFaceElectrolizeRegular() {
		return typeFaceElectrolizeRegular;
	}

	public static Typeface getTypeFaceMichroma() {
		return typeFaceMichroma;
	}

	public static Typeface getTypeFaceRobotoLight() {
		return typeFaceRobotoLight;
	}

	public static Typeface getTypeFaceRobotoBold() {
		return typeFaceRobotoBold;
	}
}