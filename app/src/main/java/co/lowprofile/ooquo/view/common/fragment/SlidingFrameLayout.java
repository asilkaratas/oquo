package co.lowprofile.ooquo.view.common.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class SlidingFrameLayout extends FrameLayout
{
    public SlidingFrameLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public float getFractionX()
    {
        final WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return (width == 0) ? 0 : getX() / (float) width;
    }

    public void setFractionX(float fractionX) {
        final WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        setX((width > 0) ? (fractionX * width) : 0);
    }
}
