package id.co.viva.pialadunia.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ResizableViewPager extends ViewPager {
	public ResizableViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    
	    View view = getChildAt(0);
	    for (int i = 0; i < getChildCount(); i++) {
	        View child = getChildAt(i);
	        //if (child != null && child.getVisibility() != GONE) {
	            child.measure(widthMeasureSpec, heightMeasureSpec);
	            if(child.getHeight() > view.getHeight()) {
	            	view = child;
	            }
	        //}
	    }
	    
	    if (view != null) {
	    	setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), view.getMeasuredHeight());
	    } else {
	    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }
	}
}