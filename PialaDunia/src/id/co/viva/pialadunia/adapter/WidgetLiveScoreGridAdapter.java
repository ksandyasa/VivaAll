package id.co.viva.pialadunia.adapter;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.object.FrontLiveScore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WidgetLiveScoreGridAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private int count = 0;
	private ArrayList<FrontLiveScore> data = new ArrayList<FrontLiveScore>();
	private DisplayImageOptions options;
	private String media_image_path;
	
	public WidgetLiveScoreGridAdapter(Context context, int count, ArrayList<FrontLiveScore> data) {
		this.count = count;
		this.data = data;

		media_image_path = context.getResources().getString(R.string.media_image_path);
		
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.image_empty_flag)
		.showImageForEmptyUri(R.drawable.image_empty_flag)
		.showImageOnFail(R.drawable.image_crash_flag)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.build();
	}
	
	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_live_score, parent, false);
			viewHolder.date = (TextView) convertView.findViewById(R.id.live_score_date);
			viewHolder.scores = (TextView) convertView.findViewById(R.id.live_score_scores);
			viewHolder.home = (TextView) convertView.findViewById(R.id.live_score_home);
			viewHolder.away = (TextView) convertView.findViewById(R.id.live_score_away);
			viewHolder.home_flag = (ImageView) convertView.findViewById(R.id.live_score_home_flag);
			viewHolder.away_flag = (ImageView) convertView.findViewById(R.id.live_score_away_flag);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		try { 
			date = sdf.parse(data.get(position).getMatchDate() + " " + data.get(position).getMatchTime()); 
		} catch (ParseException e) { 
			date = new Date(); 
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat("d MMM yyyy HH:mm", Locale.getDefault());
		
		viewHolder.date.setText(timeFormat.format(date) + " WIB");
		viewHolder.scores.setText(data.get(position).getHomeScore() + " - " + data.get(position).getAwayScore());
		viewHolder.home.setText(data.get(position).getNicknameA().equals("") ? data.get(position).getTeamA(): data.get(position).getNicknameA());
		viewHolder.away.setText(data.get(position).getNicknameB().equals("") ? data.get(position).getTeamB(): data.get(position).getNicknameB());
		ImageLoader.getInstance().displayImage(media_image_path + data.get(position).getTeamLogo1(), viewHolder.home_flag, options);
		ImageLoader.getInstance().displayImage(media_image_path + data.get(position).getTeamLogo2(), viewHolder.away_flag, options);
		
		return convertView;
	}
	
	private class ViewHolder {
		TextView date, home, away, scores;
		ImageView home_flag, away_flag;
	}
}