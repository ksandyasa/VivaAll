package id.co.viva.pialadunia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class NonScrollableGridView extends GridView {

	public NonScrollableGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, heightSpec);
		getLayoutParams().height = getMeasuredHeight();
	}
}