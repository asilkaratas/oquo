package co.lowprofile.ooquo.view;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;

/**
 * Created by asilkaratas on 10/22/15.
 */
public class SplashFragment extends AbstractFragment
{
    private static final String TAG = "SplashFragment";

    private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_splash;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        showLogin();
    }

    private void showLogin()
    {
        Runnable task = new Runnable()
        {
            public void run()
            {
                showFragment(LoginFragment.class);
            }
        };
        worker.schedule(task, 1, TimeUnit.SECONDS);
    }
}
