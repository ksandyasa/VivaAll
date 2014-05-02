package id.co.viva.pialadunia.fragment;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.adapter.WidgetLiveScoreGridAdapter;
import id.co.viva.pialadunia.object.FrontLiveScore;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class WidgetLiveScoreGridFragment extends Fragment {
	private WidgetLiveScoreGridAdapter adapter;
	private int count;
	private ArrayList<FrontLiveScore> data = new ArrayList<FrontLiveScore>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle args = getArguments();
		count = args.getInt("COUNT");
		data.add(new FrontLiveScore(args.getString("MATCH_ID1"), args.getString("MATCH_DATE1"), args.getString("MATCH_TIME1"), args.getString("HOME_SCORE1"), args.getString("AWAY_SCORE1"), args.getString("TEAM_A1"), args.getString("TEAM_B1"), args.getString("NICKNAME_A1"), args.getString("NICKNAME_B1"), args.getString("TEAM_LOGO_11"), args.getString("TEAM_LOGO_21")));
		if(count==2) data.add(new FrontLiveScore(args.getString("MATCH_ID2"), args.getString("MATCH_DATE2"), args.getString("MATCH_TIME2"), args.getString("HOME_SCORE2"), args.getString("AWAY_SCORE2"), args.getString("TEAM_A2"), args.getString("TEAM_B2"), args.getString("NICKNAME_A2"), args.getString("NICKNAME_B2"), args.getString("TEAM_LOGO_12"), args.getString("TEAM_LOGO_22")));
		
		View rootView = inflater.inflate(R.layout.fragment_widget_news_grid, container, false);

		adapter = new WidgetLiveScoreGridAdapter(getActivity(), count, data);

		GridView  gridView = (GridView ) rootView.findViewById(R.id.grid_news);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), data.get(position).getTeamA(), Toast.LENGTH_SHORT).show();
			}
		});

		return rootView;
	}
}