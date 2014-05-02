package id.co.viva.pialadunia.fragment;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.activity.MainActivity;
import id.co.viva.pialadunia.adapter.MenuAdapter;
import id.co.viva.pialadunia.share.Base;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DepanFragment extends ListFragment {
	private MenuAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		adapter = new MenuAdapter(getActivity());
		return inflater.inflate(R.layout.fragment_menu, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		int on_select = 0;
		for(int i=0; i<Base.getMenu().size(); i++) {
			if(Base.getMenu().get(i).isSelected()) on_select = i;
			Base.getMenu().get(i).setSelected(i==position ? true : false);
		}		
		
		if(on_select!=position) {
			if (getActivity() == null)
				return;

			if (getActivity() instanceof MainActivity) {
				MainActivity mainActivity = (MainActivity) getActivity();
				mainActivity.switchContent(position);
			}
		}
	}
	
	public void setSelected(int position) {
		adapter.setSelected(position);
	}
}