package co.lowprofile.ooquo.view.common.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by asilkaratas on 10/17/15.
 */
public class TabMenu extends LinearLayout
{
    private OnItemSelectedListener onItemSelectedListener = null;

    private View selectedView = null;

    public TabMenu(Context context)
    {
        this(context, null);
    }

    public TabMenu(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TabMenu(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    /*
    @Override
    public void onViewAdded(View child)
    {
        super.onViewAdded(child);

        child.setOnClickListener(new ChildOnClickListener());
    }

    @Override
    public void onViewRemoved(View child)
    {
        super.onViewRemoved(child);

        child.setOnClickListener(null);
    }
    */

    public void selectItem(View view)
    {
        if(selectedView == view) return;

        if(selectedView != null)
        {
            selectedView.setSelected(false);
        }
        selectedView = view;
        selectedView.setSelected(true);

        if(onItemSelectedListener != null)
        {
            onItemSelectedListener.onItemSelected(view, indexOfChild(view));
        }
    }

    public void selectItem(int index)
    {
        View view = getChildAt(index);
        selectItem(view);
    }

    public OnItemSelectedListener getOnItemSelectedListener()
    {
        return onItemSelectedListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener)
    {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    private class ChildOnClickListener implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            selectItem(v);
        }
    }

    public interface OnItemSelectedListener
    {
        void onItemSelected(View view, int index);
    }
}
