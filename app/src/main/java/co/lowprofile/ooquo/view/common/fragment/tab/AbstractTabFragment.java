package co.lowprofile.ooquo.view.common.fragment.tab;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.pager.AbstractPagerFragment;


/**
 * Created by asilkaratas on 10/21/15.
 */
public abstract class AbstractTabFragment extends AbstractPagerFragment implements TabLayout.OnTabSelectedListener
{
    private static final String TAG = "AbstractTabFragment";

    private TabLayout tabLayout;
    private TabLayout.TabLayoutOnPageChangeListener onPageChangeListener;

    private Bitmap bitmap = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        if(tabLayout != null)
        {
            List<View> tabButtons = getTabButtons();

            if(tabButtons != null)
            {
                for(int i = 0; i < tabButtons.size(); ++i)
                {
                    TabLayout.Tab tab = tabLayout.newTab();
                    tab.setCustomView(tabButtons.get(i));
                    tabLayout.addTab(tab);
                }
            }

            tabLayout.setOnTabSelectedListener(this);

            onPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
            getViewPager().addOnPageChangeListener(onPageChangeListener);
        }

        return view;
    }

    public abstract List<View> getTabButtons();

    public TabLayout getTabLayout()
    {
        return tabLayout;
    }

    public boolean isAnimateTabChange()
    {
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        getViewPager().setCurrentItem(tab.getPosition(), isAnimateTabChange());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab)
    {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab)
    {

    }

    @Override
    public void onResume()
    {
        if (bitmap != null && !bitmap.isRecycled())
        {
            bitmap.recycle();
            bitmap = null;
        }
        super.onResume();
    }

    @Override
    public void onPause()
    {
        bitmap = loadBitmapFromView(getView());
        super.onPause();
    }

    public static Bitmap loadBitmapFromView(View v)
    {
        Bitmap b = null;
        if(v.getWidth() > 0 && v.getHeight() > 0)
        {
            b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(0, 0, v.getWidth(),
                    v.getHeight());
            v.draw(c);
        }

        return b;
    }

    @Override
    public void onDestroyView()
    {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        getView().setBackground(bitmapDrawable);

        getViewPager().removeOnPageChangeListener(onPageChangeListener);
        onPageChangeListener = null;

        if(tabLayout != null)
        {
            tabLayout.setOnClickListener(null);
            tabLayout.removeAllTabs();
            tabLayout = null;
        }

        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        if (bitmap != null && !bitmap.isRecycled())
        {
            if (getView() != null)
            {
                getView().setBackground(null);
            }
            bitmap.recycle();
        }
        bitmap = null;

        super.onDestroy();
    }
}
