package id.co.viva.pialadunia.adapter;

import id.co.viva.pialadunia.fragment.WidgetNewsGridFragment;
import id.co.viva.pialadunia.object.News;
import id.co.viva.pialadunia.share.DB;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class WidgetNewsPagerAdapter extends FragmentStatePagerAdapter {
	private String category;
	private DB db;
	private ArrayList<News> data;
	
	public WidgetNewsPagerAdapter(FragmentManager fm, Context context, String category) {
		super(fm);
		
		this.category = category;
		
		db = DB.getInstance(context);
		notifyDataSetChanged();
	}
	
	@Override
	public void notifyDataSetChanged() {
		data = db.getAllFrontNews(category);
		
		super.notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int position) {
		int start = position * 2;
		int count = 0;
		
		Bundle args = new Bundle();
		args.putString("CATEGORY", category);
		for(int i=0; i< 2; i++) {
			if(start < data.size()) {
				count++;
				args.putString("ID" + count, data.get(start).getId());
				args.putString("TITLE" + count, data.get(start).getTitle());
				args.putString("DATE_PUBLISH" + count, data.get(start).getDatePublish());
				args.putString("PATH_THUMBNAIL" + count, data.get(start).getPathThumbnail());
				start++;
			}
		}
		args.putInt("COUNT", count);
		
		Fragment fragment = new WidgetNewsGridFragment();
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