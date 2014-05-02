package id.co.viva.pialadunia.fragment;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.share.Base;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WidgetHeadlineFragment extends Fragment {
	private String category, id;
	private DisplayImageOptions options;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle args = getArguments();
		category = args.getString("CATEGORY");
		id = args.getString("ID");
		String title = args.getString("TITLE");
		String path_thumbnail = args.getString("PATH_THUMBNAIL");

		String media_path = getResources().getString(R.string.media_path);
		String media_thubmnail_prefix = getResources().getString(R.string.media_thubmnail_prefix);
		String media_thubmnail_big = getResources().getString(R.string.media_thubmnail_big);

		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.image_empty_big)
		.showImageForEmptyUri(R.drawable.image_empty_big)
		.showImageOnFail(R.drawable.image_crash_big)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.build();

		View rootView = inflater.inflate(R.layout.fragment_widget_headline, container, false);

		TextView textTitle = (TextView) rootView.findViewById(R.id.headline_title);
		ImageView imageHeadline = (ImageView) rootView.findViewById(R.id.headline_image);

		textTitle.setTypeface(Base.getTypeFaceRobotoLight());
		textTitle.setText(title);		
		textTitle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), category + " " + id, Toast.LENGTH_SHORT).show();
			}
		});

		ImageLoader.getInstance().displayImage(media_path + path_thumbnail.replace(media_thubmnail_prefix, media_thubmnail_big), imageHeadline, options);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

}