package co.lowprofile.ooquo;

import android.app.Application;

import co.lowprofile.ooquo.controller.ServiceController;
import co.lowprofile.ooquo.model.AppModel;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.retro.RetroWebApi;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class OoquoApplication extends Application
{
    public static boolean isMocking = true;

    private static OoquoApplication instance = null;
    public static OoquoApplication getInstance()
    {
        return instance;
    }

    private IWebApi webApi;
    private AppModel appModel;

    public static  AppModel getAppModel()
    {
        return instance.appModel;
    }

    public static IWebApi getWebApi()
    {
        return instance.webApi;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        instance = this;

        webApi = new RetroWebApi("https://192.168.2.7:44305");
        appModel = new AppModel();
    }



}
