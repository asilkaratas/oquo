package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import co.lowprofile.ooquo.R;


/**
 * Created by asilkaratas on 10/4/15.
 */
public class TabButton extends FrameLayout
{
    private TextView titleText;
    private TextView countText;

    public TabButton(Context context)
    {
        this(context, null);
    }

    public TabButton(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TabButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        inflate(getContext(), R.layout.tab_button, this);

        titleText = (TextView)findViewById(R.id.titleText);
        countText = (TextView)findViewById(R.id.countText);
    }

    public TextView getTitleText()
    {
        return titleText;
    }
    public TextView getCountText()
    {
        return countText;
    }
}
