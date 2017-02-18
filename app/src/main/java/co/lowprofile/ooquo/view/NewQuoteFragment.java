package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.ShowAlertEvent;
import co.lowprofile.ooquo.event.TakePhotoEvent;
import co.lowprofile.ooquo.model.AppModel;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class NewQuoteFragment extends AbstractFragment
{
    private ImageView imageView;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_new_quote;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.new_quote).toString());

        imageView = (ImageView)view.findViewById(R.id.imageView);

        view.findViewById(R.id.takePhotoButton).setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        imageView.setImageBitmap(null);
        imageView = null;
        getView().findViewById(R.id.takePhotoButton).setOnClickListener(null);

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        AppModel appModel = OoquoApplication.getAppModel();

        if(appModel.isTakePhoto())
        {
            delayedSendTakePhotoEvent();
        }

        if(appModel.getBitmap() != null)
        {
            appModel.setQuoteBitmap(appModel.getBitmap());
            appModel.setBitmap(null);
        }

        if(appModel.getQuoteBitmap() != null)
        {
            imageView.setImageBitmap(appModel.getQuoteBitmap());
        }

    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    private void delayedSendTakePhotoEvent()
    {
        ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new Runnable()
        {
            public void run()
            {
                sendTakePhotoEvent();
            }
        };
        worker.schedule(task, 1, TimeUnit.SECONDS);
    }

    private void sendTakePhotoEvent()
    {
        OoquoApplication.getAppModel().setBitmap(null);
        OoquoApplication.getAppModel().setTakePhoto(false);

        TakePhotoEvent event = new TakePhotoEvent();
        EventBus.getDefault().post(event);
    }

    @Override
    protected void onBackClick()
    {
        OoquoApplication.getAppModel().setQuoteBitmap(null);
        super.onBackClick();
    }

    private void onTakePhotoClick()
    {
        sendTakePhotoEvent();
    }

    @Override
    protected void onNextClick()
    {
        if(OoquoApplication.getAppModel().getQuoteBitmap() == null)
        {
            ShowAlertEvent event = new ShowAlertEvent("Take photo first!");
            EventBus.getDefault().post(event);
            return;
        }
        showFragment(AddBookInfoFragment.class);
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.takePhotoButton:
                onTakePhotoClick();
                break;
        }
    }
}
