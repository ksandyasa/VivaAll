package id.co.viva.pialadunia.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class NewsViewPager extends ViewPager {
	private static boolean news_init = false;
	private static float news_view_width;
	private static float news_view_height;

	public NewsViewPager(Context context, AttributeSet attrs) {
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
	            if(child.getHeight() > 0) {
	            	view = child;
	            }
	        //}
	    }
	    
	    if (view != null) {
	    	if (!news_init && view.getHeight() > 0 && view.getWidth() > 0) {
	    		news_init = true;
	    		news_view_width = (float) view.getWidth(); 
	    		news_view_height = (float) view.getHeight();
	    	}
	    	int width = MeasureSpec.getSize(widthMeasureSpec);
	    	int height = (int) Math.ceil((float) width * news_view_height / news_view_width);
            setMeasuredDimension(width, height);
	    } else {
	    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }
	}
}