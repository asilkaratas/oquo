package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;

/**
 * Created by asilkaratas on 10/22/15.
 */
public class MenuFragment extends AbstractFragment
{
    private static final String TAG = "MenuFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        view.findViewById(R.id.feedButton).setOnClickListener(this);
        view.findViewById(R.id.profileButton).setOnClickListener(this);
        view.findViewById(R.id.logoutButton).setOnClickListener(this);
        view.findViewById(R.id.menuCameraButton).setOnClickListener(this);
        view.findViewById(R.id.menuSettingsButton).setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        getView().findViewById(R.id.feedButton).setOnClickListener(null);
        getView().findViewById(R.id.profileButton).setOnClickListener(null);
        getView().findViewById(R.id.logoutButton).setOnClickListener(null);
        getView().findViewById(R.id.menuCameraButton).setOnClickListener(null);
        getView().findViewById(R.id.menuSettingsButton).setOnClickListener(null);

        super.onDestroyView();
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_menu;
    }

    private void onFeedClick()
    {
        hideMenu();
    }

    private void onProfileClick()
    {
        hideMenu();

        User currentUser = OoquoApplication.getAppModel().getCurrentUser();
        showFragment(UserProfileFragment.class, UserProfileFragment.createBundle(currentUser.getId()));
    }

    private void onMenuCameraClick()
    {
        hideMenu();
        OoquoApplication.getAppModel().setTakePhoto(true);
        showFragment(NewQuoteFragment.class);
    }

    private void onMenuSettingsClick()
    {
        hideMenu();
        showFragment(SettingsFragment.class);
    }

    private void onLogoutClick()
    {
        hideMenu();
        showFragment(LoginFragment.class);
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.feedButton:
                onFeedClick();
                break;

            case R.id.profileButton:
                onProfileClick();
                break;

            case R.id.logoutButton:
                onLogoutClick();
                break;

            case R.id.menuCameraButton:
                onMenuCameraClick();
                break;

            case R.id.menuSettingsButton:
                onMenuSettingsClick();
                break;
        }
    }


}
