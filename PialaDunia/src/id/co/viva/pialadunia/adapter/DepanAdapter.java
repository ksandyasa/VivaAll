package id.co.viva.pialadunia.adapter;

import id.co.viva.pialadunia.R;
import id.co.viva.pialadunia.share.Base;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DepanAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;

	public DepanAdapter (Context context) {
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getViewTypeCount() {
		return 3;
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		switch(type) {
		case 0:
			ViewItem viewItem = null;
			if(convertView==null) {
				viewItem = new ViewItem();
				convertView = layoutInflater.inflate(R.layout.item_menu, parent, false);
				viewItem.view = (View) convertView.findViewById(R.id.view);
				viewItem.icon = (ImageView) convertView.findViewById(R.id.icon);
				viewItem.text = (TextView) convertView.findViewById(R.id.text);
				viewItem.text.setTypeface(Base.getTypeFaceElectrolizeRegular());
				convertView.setTag(viewItem);
			}else{
				viewItem = (ViewItem) convertView.getTag();
			}
			viewItem.view.setBackgroundResource(Base.getMenu().get(position).isSelected() ? R.color.menu_view_selected : R.color.menu_view);
			viewItem.icon.setImageResource(Base.getMenu().get(position).getIcon()!=0 ? Base.getMenu().get(position).getIcon() : R.drawable.ic_blank);
			viewItem.text.setText(Base.getMenu().get(position).getDescription());
		}

		return convertView;
	}

	public static class ViewItem {
		View view;
		ImageView icon;
		TextView text;
	}
}
