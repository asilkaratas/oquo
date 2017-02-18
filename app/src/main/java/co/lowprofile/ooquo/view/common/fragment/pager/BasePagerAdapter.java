package co.lowprofile.ooquo.view.common.fragment.pager;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BasePagerAdapter extends FragmentStatePagerAdapter
{
    private List<Class<? extends Fragment>> fragmentClasses;
    private List<Bundle> fragmentArguments;

    public BasePagerAdapter(FragmentManager fragmentManager, List<Class<? extends Fragment>> fragmentClasses, List<Bundle> fragmentArguments)
    {
        super(fragmentManager);
        this.fragmentClasses = fragmentClasses;
        this.fragmentArguments = fragmentArguments;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public Fragment getItem(int position)
    {
        Class<? extends Fragment> clazz = fragmentClasses.get(position);

        Fragment fragment = null;
        try
        {
            Constructor<? extends Fragment> constructor = clazz.getConstructor();
            fragment = constructor.newInstance();

        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {}
        catch (InstantiationException e)
        {}
        catch (IllegalAccessException e)
        {}

        if(fragment != null && fragmentArguments != null && position < fragmentArguments.size())
        {
            Bundle args = fragmentArguments.get(position);
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public Parcelable saveState()
    {
        return null;
    }

    @Override
    public int getCount() {
        return fragmentClasses.size();
    }
}