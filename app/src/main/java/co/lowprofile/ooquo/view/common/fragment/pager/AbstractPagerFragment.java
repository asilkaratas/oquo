package co.lowprofile.ooquo.view.common.fragment.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;


/**
 * Created by asilkaratas on 10/20/15.
 */
public abstract class AbstractPagerFragment extends AbstractFragment
{
    private static final String TAG = "AbstractPagerFragment";

    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreateView");

        View view = super.onCreateView(inflater, container, savedInstanceState);

        BasePagerAdapter adapter = new BasePagerAdapter(getChildFragmentManager(), getFragmentClasses(), getFragmentArguments());

        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        return view;
    }

    public ViewPager getViewPager()
    {
        return viewPager;
    }

    protected abstract List<Class<? extends Fragment>> getFragmentClasses();
    protected abstract List<Bundle> getFragmentArguments();

    public BasePagerAdapter getPagerAdapter()
    {
        return (BasePagerAdapter)getViewPager().getAdapter();
    }

    @Override
    public void onDestroyView()
    {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();


        viewPager.setAdapter(null);
        viewPager = null;
    }
}
