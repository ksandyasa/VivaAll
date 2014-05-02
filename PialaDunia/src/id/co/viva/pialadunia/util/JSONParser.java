package id.co.viva.pialadunia.util;

import id.co.viva.pialadunia.object.FrontHeadline;
import id.co.viva.pialadunia.object.FrontLiveScore;
import id.co.viva.pialadunia.object.News;
import id.co.viva.pialadunia.share.DB;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;


public class JSONParser {
	private DB db;
	private String error_message = "";

	public JSONParser(Context context) {
		db = DB.getInstance(context);
	}
	
	public String getErrorMessage() {
		return error_message;
	}

	public void setErrorMessage(String error_message) {
		this.error_message = error_message;
	}

	public boolean getFrontPage(String content) {
		boolean success = true;
		ArrayList<FrontHeadline> listFrontHeadline = new ArrayList<FrontHeadline>();
		ArrayList<News> listFrontNews = new ArrayList<News>();
		ArrayList<News> listFrontWoldcuptainment = new ArrayList<News>();
		ArrayList<FrontLiveScore> listFrontLiveScore = new ArrayList<FrontLiveScore>();

		try {
			JSONObject jObject = new JSONObject(content);
			try {
				JSONArray arrJSON = null;
				
				arrJSON =  jObject.getJSONArray("headline");
				for(int i=0; i<arrJSON.length(); i++) {
					try {
						JSONObject objArticle = arrJSON.getJSONObject(i);
						String id = objArticle.getString("id");
						String title = objArticle.getString("title");
						String path_thumbnail = objArticle.getString("path_thumbnail");

						listFrontHeadline.add(new FrontHeadline(id, title, path_thumbnail));
					} catch (JSONException  e) {
						success = false;
						error_message += "WRONG FORMAT DATA IN ITEMS HEADLINE\n";
						break;
					}
				}
				
				arrJSON =  jObject.getJSONArray("news");
				for(int i=0; i<arrJSON.length(); i++) {
					try {
						JSONObject objArticle = arrJSON.getJSONObject(i);
						String id = objArticle.getString("id");
						String title = objArticle.getString("title");
						String date_publish = objArticle.getString("date_publish");
						String path_thumbnail = objArticle.getString("path_thumbnail");

						listFrontNews.add(new News(id, title, date_publish, path_thumbnail));
					} catch (JSONException  e) {
						success = false;
						error_message += "WRONG FORMAT DATA IN ITEMS NEWS\n";
						break;
					}
				}
				
				arrJSON =  jObject.getJSONArray("worldcuptainment");
				for(int i=0; i<arrJSON.length(); i++) {
					try {
						JSONObject objArticle = arrJSON.getJSONObject(i);
						String id = objArticle.getString("id");
						String title = objArticle.getString("title");
						String date_publish = objArticle.getString("date_publish");
						String path_thumbnail = objArticle.getString("path_thumbnail");

						listFrontWoldcuptainment.add(new News(id, title, date_publish, path_thumbnail));
					} catch (JSONException  e) {
						success = false;
						error_message += "WRONG FORMAT DATA IN ITEMS WORLDCUPTAINMENT\n";
						break;
					}
				}
				
				arrJSON =  jObject.getJSONArray("livescore");
				for(int i=0; i<arrJSON.length(); i++) {
					try {
						JSONObject objArticle = arrJSON.getJSONObject(i);
						String match_id = objArticle.getString("match_id");
						String match_date = objArticle.getString("match_date");
						String match_time = objArticle.getString("match_time");
						String home_score = objArticle.getString("home_score");
						String away_score = objArticle.getString("away_score");
						String team_a = objArticle.getString("team_a");
						String team_b = objArticle.getString("team_b");
						String nickname_a = objArticle.getString("nickname_a");
						String nickname_b = objArticle.getString("nickname_b");
						String team_logo_1 = objArticle.getString("team_logo_1");
						String team_logo_2 = objArticle.getString("team_logo_2");

						listFrontLiveScore.add(new FrontLiveScore(match_id, match_date, match_time, home_score, away_score, team_a, team_b, nickname_a, nickname_b, team_logo_1, team_logo_2));
					} catch (JSONException  e) {
						success = false;
						error_message += "WRONG FORMAT DATA IN ITEMS LIVE SCORE\n";
						break;
					}
				}
			} catch (JSONException  e) {
				success = false;
				error_message = "WRONG FORMAT DATA IN OBJECT";
			}
		} catch (JSONException e) {
			success = false;
			error_message = "INVALID DATA";
		}

		if(success) {
			db.deleteAllFrontHeadline();
			db.addAllFrontHeadline(listFrontHeadline);
			
			db.deleteAllFrontNews("news");
			db.addAllFrontNews(listFrontNews, "news");
			
			db.deleteAllFrontNews("worldcuptainment");
			db.addAllFrontNews(listFrontWoldcuptainment, "worldcuptainment");
			
			db.deleteAllFrontLiveScore();
			db.addAllFrontLiveScore(listFrontLiveScore);
		}

		return success;
	}
}