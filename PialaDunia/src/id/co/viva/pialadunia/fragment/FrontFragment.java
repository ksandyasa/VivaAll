package id.co.viva.pialadunia.fragment;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.adapter.HeadlinePagerAdapter;
import id.co.viva.pialadunia.adapter.WidgetLiveScorePagerAdapter;
import id.co.viva.pialadunia.adapter.WidgetNewsPagerAdapter;
import id.co.viva.pialadunia.util.FetchContent;
import id.co.viva.pialadunia.util.JSONParser;
import id.co.viva.pialadunia.util.NetworkManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.viewpagerindicator.UnderlinePageIndicator;

public class FrontFragment extends Fragment {
	private PullToRefreshScrollView pullToRefresh;
	private ViewPager pagerHeadline, pagerNews, pagerWorldcuptainment, pagerLiveScore;
	private UnderlinePageIndicator indicatorHeadline, indicatorNews, indicatorWorldcuptainment, indicatorLiveScore;
	private ImageView viewHeadline;
	private TextView viewNews, viewWorldcuptainment, viewLiveScore;
	private View separatorNews, separatorWorldcuptainment, separatorLiveScore;
	private HeadlinePagerAdapter adapterHeadline;
	private WidgetNewsPagerAdapter adapterNews, adapterWorldcuptainment;
	private WidgetLiveScorePagerAdapter adapterLiveScore;
	private DownloadJSONTask downloadJSONTask = null;
	private static long last_load_front = System.currentTimeMillis();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_front, container, false);

		pullToRefresh = (PullToRefreshScrollView) rootView.findViewById(R.id.scroll);
		pagerHeadline = (ViewPager) rootView.findViewById(R.id.pager_headline);
		pagerNews = (ViewPager) rootView.findViewById(R.id.pager_news);
		pagerWorldcuptainment = (ViewPager) rootView.findViewById(R.id.pager_worldcuptainment);
		pagerLiveScore = (ViewPager) rootView.findViewById(R.id.pager_live_score);

		indicatorHeadline = (UnderlinePageIndicator) rootView.findViewById(R.id.indicator_headline);
		indicatorNews = (UnderlinePageIndicator) rootView.findViewById(R.id.indicator_news);
		indicatorWorldcuptainment = (UnderlinePageIndicator) rootView.findViewById(R.id.indicator_worldcuptainment);
		indicatorLiveScore = (UnderlinePageIndicator) rootView.findViewById(R.id.indicator_live_score);
		
		viewHeadline = (ImageView) rootView.findViewById(R.id.view_headline);
		viewNews = (TextView) rootView.findViewById(R.id.news);
		separatorNews = (View)  rootView.findViewById(R.id.separator1);
		viewWorldcuptainment = (TextView) rootView.findViewById(R.id.worldcuptainment);
		separatorWorldcuptainment = (View)  rootView.findViewById(R.id.separator2);
		viewLiveScore = (TextView) rootView.findViewById(R.id.live_score);
		separatorLiveScore = (View)  rootView.findViewById(R.id.separator3);
		
		pullToRefresh.setScrollingWhileRefreshingEnabled(true);
		pullToRefresh.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				DownloadJSON(true);
			}
		});

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		adapterHeadline = new HeadlinePagerAdapter(getFragmentManager(), getActivity());
		adapterNews = new WidgetNewsPagerAdapter(getFragmentManager(), getActivity(), "news");
		adapterWorldcuptainment = new WidgetNewsPagerAdapter(getFragmentManager(), getActivity(), "worldcuptainment");
		adapterLiveScore = new WidgetLiveScorePagerAdapter(getFragmentManager(), getActivity());

		pagerHeadline.setAdapter(adapterHeadline);
		pagerNews.setAdapter(adapterNews);
		pagerWorldcuptainment.setAdapter(adapterWorldcuptainment);
		pagerLiveScore.setAdapter(adapterLiveScore);

		indicatorHeadline.setViewPager(pagerHeadline);
		indicatorNews.setViewPager(pagerNews);
		indicatorWorldcuptainment.setViewPager(pagerWorldcuptainment);
		indicatorLiveScore.setViewPager(pagerLiveScore);

		setViewHeadline(adapterHeadline.getCount()==0 ? View.GONE : View.VISIBLE);
		setViewNews(adapterNews.getCount()==0 ? View.GONE : View.VISIBLE);
		setViewWorldcuptainment(adapterWorldcuptainment.getCount()==0 ? View.GONE : View.VISIBLE);
		setViewLiveScore(adapterLiveScore.getCount()==0 ? View.GONE : View.VISIBLE);
		
		if(System.currentTimeMillis() - last_load_front > 1000 * 60 * 10 ) {
			last_load_front = System.currentTimeMillis(); 
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					DownloadJSON(false);
					pullToRefresh.setRefreshing();
				}
			}, 1000);
		}
	}

	private void DownloadJSON(boolean show_error) {
		if(NetworkManager.isConnectivityAvailable(getActivity(), show_error)) {
			if(downloadJSONTask != null){
				downloadJSONTask.cancel(true);
				downloadJSONTask = null;
			}
			downloadJSONTask = new DownloadJSONTask(getActivity());
			downloadJSONTask.execute(new String[]{getResources().getString(R.string.url_front_page)});
		} else {
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					pullToRefresh.onRefreshComplete();
				}
			}, 300);
		}
	}

	private void setViewHeadline(int view) {
		viewHeadline.setVisibility(view);
		pagerHeadline.setVisibility(view);
		indicatorHeadline.setVisibility(view);
	}
	
	private void setViewNews(int view) {
		viewNews.setVisibility(view);
		separatorNews.setVisibility(view);
		indicatorNews.setVisibility(view);
	}
	
	private void setViewWorldcuptainment(int view) {
		viewWorldcuptainment.setVisibility(view);
		separatorWorldcuptainment.setVisibility(view);
		indicatorWorldcuptainment.setVisibility(view);
	}
	
	private void setViewLiveScore(int view) {
		viewLiveScore.setVisibility(view);
		separatorLiveScore.setVisibility(view);
		indicatorLiveScore.setVisibility(view);
	}
	
	private class DownloadJSONTask extends AsyncTask<String, Integer, FetchContent> {
		private Context context;

		public DownloadJSONTask(Context context) {
			this.context = context;
		}
		@Override
		protected FetchContent doInBackground(String... urls) {
			FetchContent fetchContent = new FetchContent();
			fetchContent.setUrl(urls[0]);
			fetchContent.fetchURL();

			return fetchContent;
		}

		@Override
		protected void onPostExecute(FetchContent result) {
			if(!isCancelled()) {
				if(result.isSuccess()) {
					JSONParser parser = new JSONParser(context);
					if(!parser.getFrontPage(result.getResponseBody())) {
						Toast.makeText(context, parser.getErrorMessage(), Toast.LENGTH_SHORT).show();
					} else {
						adapterHeadline.notifyDataSetChanged();
						adapterNews.notifyDataSetChanged();
						adapterWorldcuptainment.notifyDataSetChanged();
						adapterLiveScore.notifyDataSetChanged();
						
						setViewHeadline(adapterHeadline.getCount()==0 ? View.GONE : View.VISIBLE);
						setViewNews(adapterNews.getCount()==0 ? View.GONE : View.VISIBLE);
						setViewWorldcuptainment(adapterWorldcuptainment.getCount()==0 ? View.GONE : View.VISIBLE);
						setViewLiveScore(adapterLiveScore.getCount()==0 ? View.GONE : View.VISIBLE);
					}
				} else {
					Toast.makeText(context, result.getErrorMessage(), Toast.LENGTH_SHORT).show();
				}
			}

			pullToRefresh.onRefreshComplete();
		}
	}
}