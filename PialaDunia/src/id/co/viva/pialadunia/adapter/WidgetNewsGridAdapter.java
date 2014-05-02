package id.co.viva.pialadunia.adapter;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.object.News;

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

public class WidgetNewsGridAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private int count = 0;
	private ArrayList<News> data = new ArrayList<News>();
	private DisplayImageOptions options;
	private String media_path, media_thubmnail_prefix, media_thubmnail_small;

	public WidgetNewsGridAdapter(Context context, int count, ArrayList<News> data) {
		this.count = count;
		this.data = data;

		media_path = context.getResources().getString(R.string.media_path);
		media_thubmnail_prefix = context.getResources().getString(R.string.media_thubmnail_prefix);
		media_thubmnail_small = context.getResources().getString(R.string.media_thubmnail_small);
		
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.image_empty_small)
		.showImageForEmptyUri(R.drawable.image_empty_small)
		.showImageOnFail(R.drawable.image_crash_small)
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
			convertView = layoutInflater.inflate(R.layout.item_widget_news, parent, false);
			viewHolder.time = (TextView) convertView.findViewById(R.id.news_time);
			viewHolder.title = (TextView) convertView.findViewById(R.id.news_title);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.news_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		try { 
			date = sdf.parse(data.get(position).getDatePublish()); 
		} catch (ParseException e) { 
			date = new Date(); 
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
		
		viewHolder.time.setText(timeFormat.format(date));
		viewHolder.title.setText(data.get(position).getTitle());
		ImageLoader.getInstance().displayImage(media_path + data.get(position).getPathThumbnail().replace(media_thubmnail_prefix, media_thubmnail_small), viewHolder.image, options);
		
		return convertView;
	}

	private class ViewHolder {
		TextView time;
		ImageView image;
		TextView title;
	}
}