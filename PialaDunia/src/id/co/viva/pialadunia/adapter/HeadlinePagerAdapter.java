package id.co.viva.pialadunia.adapter;

import java.util.ArrayList;

import id.co.viva.pialadunia.fragment.WidgetHeadlineFragment;
import id.co.viva.pialadunia.object.FrontHeadline;
import id.co.viva.pialadunia.share.DB;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class HeadlinePagerAdapter extends FragmentStatePagerAdapter {
	private DB db;
	private ArrayList<FrontHeadline> data;
	
	public HeadlinePagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		
		db = DB.getInstance(context);
		notifyDataSetChanged();
	}
	
	@Override
	public void notifyDataSetChanged() {
		data = db.getAllFrontHeadline();
		
		super.notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = new WidgetHeadlineFragment();
		Bundle args = new Bundle();
		args.putString("CATEGORY", "headline");
		args.putString("ID", data.get(position).getId());
		args.putString("TITLE", data.get(position).getTitle());
		args.putString("PATH_THUMBNAIL", data.get(position).getPathThumbnail());
		fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public int getCount() {
		return data.size();
	}
}