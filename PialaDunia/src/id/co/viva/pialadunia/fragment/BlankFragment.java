package id.co.viva.pialadunia.fragment;

import id.co.viva.pialadunia.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlankFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
		Bundle args = getArguments();
		TextView text = (TextView) rootView.findViewById(R.id.blank_text);
		text.setText(args.getString("text"));
		return rootView;
	}
}
