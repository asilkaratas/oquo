package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class SettingsFragment extends AbstractFragment
{
    @Override
    protected int getLayout()
    {
        return R.layout.fragment_settings;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.settings).toString());

        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
