package id.co.viva.pialadunia.fragment;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.adapter.WidgetNewsGridAdapter;
import id.co.viva.pialadunia.object.News;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class WidgetNewsGridFragment extends Fragment {
	private WidgetNewsGridAdapter adapter;
	private String category;
	private int count;
	private ArrayList<News> data = new ArrayList<News>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle args = getArguments();
		category = args.getString("CATEGORY");
		count = args.getInt("COUNT");
		data.add(new News(args.getString("ID1"), args.getString("TITLE1"), args.getString("DATE_PUBLISH1"), args.getString("PATH_THUMBNAIL1")));
		if(count==2) data.add(new News(args.getString("ID2"), args.getString("TITLE2"), args.getString("DATE_PUBLISH2"), args.getString("PATH_THUMBNAIL2")));
		
		View rootView = inflater.inflate(R.layout.fragment_widget_news_grid, container, false);

		adapter = new WidgetNewsGridAdapter(getActivity(), count, data);

		GridView  gridView = (GridView ) rootView.findViewById(R.id.grid_news);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), category + " " + data.get(position).getTitle(), Toast.LENGTH_SHORT).show();
			}
		});

		return rootView;
	}
}