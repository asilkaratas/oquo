package co.lowprofile.ooquo.view.common.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import co.lowprofile.ooquo.R;

/**
 * Created by asilkaratas on 10/22/15.
 */
public class BaseActivity extends AppCompatActivity
{
    private static final String TAG = "BaseActivity";
    private boolean isMenuShow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //hideMenuInstant();
    }

    public void showFragment(Class<? extends Fragment> fragmentClass, boolean isModal)
    {
        showFragment(fragmentClass, null, isModal);
    }

    public void showFragment(Class<? extends Fragment> fragmentClass)
    {
        showFragment(fragmentClass, null, false);
    }

    public void showFragment(Class<? extends Fragment> fragmentClass, Bundle args, boolean isModal)
    {
        Fragment fragment = null;
        try
        {
            Constructor<? extends Fragment> constructor = fragmentClass.getConstructor();
            fragment = constructor.newInstance();

        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {

        }
        catch (InstantiationException e)
        {

        }
        catch (IllegalAccessException e)
        {}

        if(fragment != null && args != null)
        {
            fragment.setArguments(args);
        }

        if(fragment != null)
        {
            showFragment(fragment, isModal);
        }
    }

    private void showFragment(Fragment fragment, boolean isModal)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit);

        if(isModal)
        {
            transaction.setCustomAnimations(R.anim.fragment_modal_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_modal_pop_exit);
        }
        else
        {
            transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit);
        }

        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack("");
        transaction.commit();

    }

    public void toggleMenu()
    {
        if(isMenuShow)
        {
            hideMenu();
        }
        else
        {
            showMenu();
        }
    }

    public void showMenu()
    {
        if(!isMenuShow)
        {
            isMenuShow = true;
            FrameLayout menuContainer = (FrameLayout)findViewById(R.id.menuContainer);
            menuContainer.setVisibility(View.VISIBLE);

            FrameLayout container = (FrameLayout)findViewById(R.id.container);
            ObjectAnimator animator = ObjectAnimator.ofFloat(container, "fractionX", 0.0f, 0.8f);
            animator.setDuration(300);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.start();

        }
    }

    public void hideMenu()
    {
        if(isMenuShow)
        {
            isMenuShow = false;

            FrameLayout container = (FrameLayout) findViewById(R.id.container);
            ObjectAnimator animator = ObjectAnimator.ofFloat(container, "fractionX", 0.8f, 0.0f);
            animator.setDuration(300);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.addListener(new Animator.AnimatorListener()
            {
                @Override
                public void onAnimationStart(Animator animation)
                {

                }

                @Override
                public void onAnimationEnd(Animator animation)
                {
                    FrameLayout menuContainer = (FrameLayout) findViewById(R.id.menuContainer);
                    menuContainer.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation)
                {

                }

                @Override
                public void onAnimationRepeat(Animator animation)
                {

                }
            });
            animator.start();

        }
    }

    public void back()
    {
        getSupportFragmentManager().popBackStack();
    }

    public void showAlert(String message)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
