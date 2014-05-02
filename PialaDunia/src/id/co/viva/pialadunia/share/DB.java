package id.co.viva.pialadunia.share;

import id.co.viva.pialadunia.object.FrontHeadline;
import id.co.viva.pialadunia.object.FrontLiveScore;
import id.co.viva.pialadunia.object.News;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
	private static DB instance;
	public static DB getInstance(Context context) {
		if(instance == null) instance = new DB(context);
		return instance;
	}

	private static SQLiteDatabase db;

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "VIVAPialaDunia";
	private static final String TABLE_FRONT_HEADLINE = "front_headline";
	private static final String TABLE_FRONT_NEWS = "front_news";
	private static final String TABLE_FRONT_LIVE_SCORE = "front_live_score";

	private static final String FRONT_HEADLINE_KEY = "key";
	private static final String FRONT_HEADLINE_ID = "id";
	private static final String FRONT_HEADLINE_TITLE = "title";
	private static final String FRONT_HEADLINE_PATH_THUMBNAIL = "path_thumbnail";
	private static final String FRONT_HEADLINE_TS = "ts";

	private static final String FRONT_NEWS_KEY = "key";
	private static final String FRONT_NEWS_ID = "id";
	private static final String FRONT_NEWS_TITLE = "title";
	private static final String FRONT_NEWS_DATE_PUBLISH = "date_publish";
	private static final String FRONT_NEWS_PATH_THUMBNAIL = "path_thumbnail";
	private static final String FRONT_NEWS_CATEGORY = "category";
	private static final String FRONT_NEWS_TS = "ts";

	private static final String FRONT_LIVE_SCORE_KEY = "key";
	private static final String FRONT_LIVE_SCORE_MATCH_ID = "match_id";
	private static final String FRONT_LIVE_SCORE_MATCH_DATE = "match_date";
	private static final String FRONT_LIVE_SCORE_MATCH_TIME = "match_time";
	private static final String FRONT_LIVE_SCORE_HOME_SCORE = "home_score";
	private static final String FRONT_LIVE_SCORE_AWAY_SCORE = "away_score";
	private static final String FRONT_LIVE_SCORE_TEAM_A = "team_a";
	private static final String FRONT_LIVE_SCORE_TEAM_B = "team_b";
	private static final String FRONT_LIVE_SCORE_NICKNAME_A = "nickname_a";
	private static final String FRONT_LIVE_SCORE_NICKNAME_B = "nickname_b";
	private static final String FRONT_LIVE_SCORE_TEAM_LOGO_1 = "team_logo_1";
	private static final String FRONT_LIVE_SCORE_TEAM_LOGO_2 = "team_logo_2";
	private static final String FRONT_LIVE_SCORE_TS = "ts";

	public DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		db = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_FRONT_FAVORITE = "CREATE TABLE " + TABLE_FRONT_HEADLINE + " ("
				+ FRONT_HEADLINE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ FRONT_HEADLINE_ID + " TEXT, "
				+ FRONT_HEADLINE_TITLE + " TEXT, "
				+ FRONT_HEADLINE_PATH_THUMBNAIL + " TEXT,"
				+ FRONT_HEADLINE_TS + " TEXT)";

		String CREATE_TABLE_FRONT_NEWS = "CREATE TABLE " + TABLE_FRONT_NEWS + " ("
				+ FRONT_NEWS_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ FRONT_NEWS_ID + " TEXT, "
				+ FRONT_NEWS_TITLE + " TEXT, "
				+ FRONT_NEWS_DATE_PUBLISH + " TEXT, "
				+ FRONT_NEWS_PATH_THUMBNAIL + " TEXT,"
				+ FRONT_NEWS_CATEGORY + " TEXT,"
				+ FRONT_NEWS_TS + " TEXT)";

		String CREATE_TABLE_FRONT_LIVE_SCORE = "CREATE TABLE " + TABLE_FRONT_LIVE_SCORE + " ("
				+ FRONT_LIVE_SCORE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ FRONT_LIVE_SCORE_MATCH_ID + " TEXT, "
				+ FRONT_LIVE_SCORE_MATCH_DATE + " TEXT, "
				+ FRONT_LIVE_SCORE_MATCH_TIME + " TEXT, "
				+ FRONT_LIVE_SCORE_HOME_SCORE + " TEXT,"
				+ FRONT_LIVE_SCORE_AWAY_SCORE + " TEXT,"
				+ FRONT_LIVE_SCORE_TEAM_A  + " TEXT,"
				+ FRONT_LIVE_SCORE_TEAM_B  + " TEXT,"
				+ FRONT_LIVE_SCORE_NICKNAME_A  + " TEXT,"
				+ FRONT_LIVE_SCORE_NICKNAME_B  + " TEXT,"
				+ FRONT_LIVE_SCORE_TEAM_LOGO_1  + " TEXT,"
				+ FRONT_LIVE_SCORE_TEAM_LOGO_2  + " TEXT,"
				+ FRONT_LIVE_SCORE_TS + " TEXT)";

		try {
			db.beginTransaction();
			db.execSQL(CREATE_TABLE_FRONT_FAVORITE);
			db.execSQL(CREATE_TABLE_FRONT_NEWS);
			db.execSQL(CREATE_TABLE_FRONT_LIVE_SCORE);
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public ArrayList<FrontHeadline> getAllFrontHeadline() {
		ArrayList<FrontHeadline> result = new ArrayList<FrontHeadline>();

		Cursor cursor = db.query(
				TABLE_FRONT_HEADLINE,
				new String[]{FRONT_HEADLINE_ID, FRONT_HEADLINE_TITLE, FRONT_HEADLINE_PATH_THUMBNAIL},
				null, null, null, null, FRONT_HEADLINE_KEY, null);
		if(cursor.moveToFirst()){
			do{
				FrontHeadline frontHeadline = new FrontHeadline(cursor.getString(0), cursor.getString(1), cursor.getString(2));
				result.add(frontHeadline);
			}while(cursor.moveToNext());
		}
		cursor.close();

		return result;
	}

	public void addAllFrontHeadline(ArrayList<FrontHeadline> frontHeadlines) {
		for(FrontHeadline frontHeadline : frontHeadlines) {
			ContentValues values = new ContentValues();
			values.put(FRONT_HEADLINE_ID, frontHeadline.getId());
			values.put(FRONT_HEADLINE_TITLE, frontHeadline.getTitle());
			values.put(FRONT_HEADLINE_PATH_THUMBNAIL, frontHeadline.getPathThumbnail());
			values.put(FRONT_HEADLINE_TS, System.currentTimeMillis());

			db.insert(TABLE_FRONT_HEADLINE, null, values);
		}
	}

	public void deleteAllFrontHeadline() {
		db.delete(TABLE_FRONT_HEADLINE, null, null);
	}

	public ArrayList<News> getAllFrontNews(String category) {
		ArrayList<News> result = new ArrayList<News>();

		Cursor cursor = db.query(
				TABLE_FRONT_NEWS,
				new String[]{FRONT_NEWS_ID, FRONT_NEWS_TITLE, FRONT_NEWS_DATE_PUBLISH, FRONT_NEWS_PATH_THUMBNAIL},
				FRONT_NEWS_CATEGORY + "=?",
				new String[]{category},
				null, null, FRONT_NEWS_KEY);
		if(cursor.moveToFirst()){
			do{
				News frontNews = new News(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
				result.add(frontNews);
			}while(cursor.moveToNext());
		}
		cursor.close();

		return result;
	}

	public void addAllFrontNews(ArrayList<News> frontNewses, String category) {
		for(News frontNews : frontNewses) {
			ContentValues values = new ContentValues();
			values.put(FRONT_NEWS_ID, frontNews.getId());
			values.put(FRONT_NEWS_TITLE, frontNews.getTitle());
			values.put(FRONT_NEWS_DATE_PUBLISH, frontNews.getDatePublish());
			values.put(FRONT_NEWS_PATH_THUMBNAIL, frontNews.getPathThumbnail());
			values.put(FRONT_NEWS_CATEGORY, category);
			values.put(FRONT_NEWS_TS, System.currentTimeMillis());

			db.insert(TABLE_FRONT_NEWS, null, values);
		}
	}

	public void deleteAllFrontNews(String category) {
		db.delete(TABLE_FRONT_NEWS, FRONT_NEWS_CATEGORY + "=?", new String[]{category});
	}

	public ArrayList<FrontLiveScore> getAllFrontLiveScore() {
		ArrayList<FrontLiveScore> result = new ArrayList<FrontLiveScore>();

		Cursor cursor = db.query(
				TABLE_FRONT_LIVE_SCORE,
				new String[]{FRONT_LIVE_SCORE_MATCH_ID, FRONT_LIVE_SCORE_MATCH_DATE, FRONT_LIVE_SCORE_MATCH_TIME, FRONT_LIVE_SCORE_HOME_SCORE, FRONT_LIVE_SCORE_AWAY_SCORE, FRONT_LIVE_SCORE_TEAM_A, FRONT_LIVE_SCORE_TEAM_B, FRONT_LIVE_SCORE_NICKNAME_A, FRONT_LIVE_SCORE_NICKNAME_B, FRONT_LIVE_SCORE_TEAM_LOGO_1, FRONT_LIVE_SCORE_TEAM_LOGO_2},
				null, null, null, null, FRONT_LIVE_SCORE_KEY, null);
		if(cursor.moveToFirst()){
			do{
				FrontLiveScore frontLiveScore = new FrontLiveScore(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
				result.add(frontLiveScore);
			}while(cursor.moveToNext());
		}
		cursor.close();

		return result;
	}

	public void addAllFrontLiveScore(ArrayList<FrontLiveScore> frontLiveScores) {
		for(FrontLiveScore frontLiveScore : frontLiveScores) {
			ContentValues values = new ContentValues();
			values.put(FRONT_LIVE_SCORE_MATCH_ID, frontLiveScore.getMatchId());
			values.put(FRONT_LIVE_SCORE_MATCH_DATE, frontLiveScore.getMatchDate());
			values.put(FRONT_LIVE_SCORE_MATCH_TIME, frontLiveScore.getMatchTime());
			values.put(FRONT_LIVE_SCORE_HOME_SCORE, frontLiveScore.getHomeScore());
			values.put(FRONT_LIVE_SCORE_AWAY_SCORE, frontLiveScore.getAwayScore());
			values.put(FRONT_LIVE_SCORE_TEAM_A, frontLiveScore.getTeamA());
			values.put(FRONT_LIVE_SCORE_TEAM_B, frontLiveScore.getTeamB());
			values.put(FRONT_LIVE_SCORE_NICKNAME_A, frontLiveScore.getNicknameA());
			values.put(FRONT_LIVE_SCORE_NICKNAME_B, frontLiveScore.getNicknameB());
			values.put(FRONT_LIVE_SCORE_TEAM_LOGO_1, frontLiveScore.getTeamLogo1());
			values.put(FRONT_LIVE_SCORE_TEAM_LOGO_2, frontLiveScore.getTeamLogo2());
			values.put(FRONT_LIVE_SCORE_TS, System.currentTimeMillis());

			db.insert(TABLE_FRONT_LIVE_SCORE, null, values);
		}
	}

	public void deleteAllFrontLiveScore() {
		db.delete(TABLE_FRONT_LIVE_SCORE, null, null);
	}
}