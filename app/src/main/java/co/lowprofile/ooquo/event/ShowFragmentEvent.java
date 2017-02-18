package co.lowprofile.ooquo.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by asilkaratas on 2/1/16.
 */
public class ShowFragmentEvent
{
    private Class<? extends Fragment> fragmentClass;
    private Bundle args;
    private boolean isModal;

    public ShowFragmentEvent(Class<? extends Fragment> fragmentClass)
    {
        this(fragmentClass, null, false);
    }

    public ShowFragmentEvent(Class<? extends Fragment> fragmentClass, Bundle args)
    {
        this(fragmentClass, args, false);
    }

    public ShowFragmentEvent(Class<? extends Fragment> fragmentClass, Bundle args, boolean isModal)
    {
        this.fragmentClass = fragmentClass;
        this.args = args;
        this.isModal = isModal;
    }


    public Class<? extends Fragment> getFragmentClass()
    {
        return fragmentClass;
    }

    public Bundle getArgs()
    {
        return args;
    }

    public boolean isModal()
    {
        return isModal;
    }
}
