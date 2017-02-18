package co.lowprofile.ooquo.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.event.ShowAlertEvent;
import co.lowprofile.ooquo.event.ShowFragmentEvent;
import co.lowprofile.ooquo.event.TakePhotoEvent;
import co.lowprofile.ooquo.view.common.fragment.BaseActivity;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity
{
    private static final String TAG = "MainActivity";

    private Class<? extends Fragment> fragmentClass = LoginFragment.class;
    //private Class<? extends Fragment> fragmentClass = HRRoomTypeSelectionFragment.class;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        showFragment(fragmentClass);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Bitmap bitmap = null;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");

            Log.d(TAG, "bitmap:" + bitmap);
            OoquoApplication.getAppModel().setBitmap(bitmap);
        }

        //EventBus.getDefault().post(new TakePhotoResponseEvent(bitmap));
    }

    public void onEventMainThread(TakePhotoEvent event)
    {
        dispatchTakePictureIntent();
    }

    public void onEventMainThread(ShowAlertEvent event)
    {
        showAlert(event.getMessage());
    }

    public void onEventMainThread(ShowFragmentEvent event)
    {
        showFragment(event.getFragmentClass(), event.getArgs(), event.isModal());
    }
}
