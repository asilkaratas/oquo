package co.lowprofile.ooquo.view.common.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.ui.HeaderView;


/**
 * Created by asilkaratas on 10/22/15.
 */
public abstract class AbstractFragment extends Fragment implements View.OnClickListener
{
    private static final String TAG = "AbstractFragment";

    private HeaderView headerView;
    private Button nextButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(getLayout(), null, false);

        headerView = (HeaderView)view.findViewById(R.id.headerView);
        if(headerView != null)
        {
            headerView.setOnClickListener(this);
        }

        nextButton = (Button)view.findViewById(R.id.nextButton);
        if(nextButton != null)
        {
            nextButton.setOnClickListener(this);
        }

        return view;
    }

    protected abstract int getLayout();

    public HeaderView getHeaderView()
    {
        return headerView;
    }

    public Button getNextButton()
    {
        return nextButton;
    }

    protected void hideMenu()
    {
        BaseActivity activity = (BaseActivity)getActivity();
        activity.hideMenu();
    }

    protected void onBackClick()
    {
        BaseActivity activity = (BaseActivity)getActivity();
        activity.back();
    }

    protected void onMenuClick()
    {
        Log.d(TAG, "onMenuClick");
        BaseActivity activity = (BaseActivity)getActivity();
        activity.toggleMenu();
    }

    protected void onCancelClick()
    {

    }

    protected void onCameraClick()
    {

    }

    protected void onNextClick()
    {

    }

    @Override
    public void onClick(View v)
    {
        //Log.d(TAG, "OnClick:" + v.getId() + " tag:" + v.getTag() + " R.id.btnBack:" + R.id.btnBack);

        switch (v.getId())
        {
            case R.id.backButton:
                onBackClick();
                break;

            case R.id.cameraButton:
                onCameraClick();
                break;

            case R.id.menuButton:
                onMenuClick();
                break;

            case R.id.nextButton:
                onNextClick();
                break;
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        if(headerView != null)
        {
            headerView.setOnClickListener(null);
            headerView = null;
        }

        if(nextButton != null)
        {
            nextButton.setOnClickListener(null);
            nextButton = null;
        }
    }

    public void showFragment(Class<? extends Fragment> fragmentClass, boolean isModal)
    {
        BaseActivity activity = (BaseActivity)getActivity();
        activity.showFragment(fragmentClass, isModal);
    }

    public void showFragment(Class<? extends Fragment> fragmentClass)
    {
        BaseActivity activity = (BaseActivity)getActivity();
        activity.showFragment(fragmentClass);
    }

    public void showFragment(Class<? extends Fragment> fragmentClass, Bundle args)
    {
        BaseActivity activity = (BaseActivity)getActivity();
        activity.showFragment(fragmentClass, args, false);
    }

    public void showAlert(String message)
    {
        BaseActivity activity = (BaseActivity)getActivity();
        activity.showAlert(message);
    }

}
