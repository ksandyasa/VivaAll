package id.co.viva.pialadunia.adapter;

import id.co.viva.pialadunia.fragment.WidgetLiveScoreGridFragment;
import id.co.viva.pialadunia.object.FrontLiveScore;
import id.co.viva.pialadunia.share.DB;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class WidgetLiveScorePagerAdapter extends FragmentStatePagerAdapter {
	private DB db;
	private ArrayList<FrontLiveScore> data;
	
	public WidgetLiveScorePagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		
		db = DB.getInstance(context);
		notifyDataSetChanged();
	}
	
	@Override
	public void notifyDataSetChanged() {
		data = db.getAllFrontLiveScore();
		
		super.notifyDataSetChanged();
	}
	
	@Override
	public Fragment getItem(int position) {
		int start = position * 2;
		int count = 0;
		
		Bundle args = new Bundle();
		for(int i=0; i< 2; i++) {
			if(start < data.size()) {
				count++;
				args.putString("MATCH_ID" + count, data.get(start).getMatchId());
				args.putString("MATCH_DATE" + count, data.get(start).getMatchDate());
				args.putString("MATCH_TIME" + count, data.get(start).getMatchTime());
				args.putString("HOME_SCORE" + count, data.get(start).getHomeScore());
				args.putString("AWAY_SCORE" + count, data.get(start).getAwayScore());
				args.putString("TEAM_A" + count, data.get(start).getTeamA());
				args.putString("TEAM_B" + count, data.get(start).getTeamB());
				args.putString("NICKNAME_A" + count, data.get(start).getNicknameA());
				args.putString("NICKNAME_B" + count, data.get(start).getNicknameB());
				args.putString("TEAM_LOGO_1" + count, data.get(start).getTeamLogo1());
				args.putString("TEAM_LOGO_2" + count, data.get(start).getTeamLogo2());
				start++;
			}
		}
		args.putInt("COUNT", count);
		
		Fragment fragment = new WidgetLiveScoreGridFragment();
		fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public int getItemPosition(Object object) {
	    return POSITION_NONE;
	}
	
	@Override
	public int getCount() {
		int count = (int) Math.ceil((double) data.size() / 2);
		return count;
	}
}