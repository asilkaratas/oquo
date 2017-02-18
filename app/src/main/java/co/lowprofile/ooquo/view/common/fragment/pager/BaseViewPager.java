package co.lowprofile.ooquo.view.common.fragment.pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by asilkaratas on 10/27/15.
 */
public class BaseViewPager extends ViewPager
{
    private boolean swipeable = false;
    public BaseViewPager(Context context)
    {
        this(context, null);
    }

    public BaseViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return isSwipeable();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return isSwipeable();
    }


    public boolean isSwipeable()
    {
        return swipeable;
    }

    public void setSwipeable(boolean swipeable)
    {
        this.swipeable = swipeable;
    }
}
