package co.lowprofile.ooquo.service.webapi.retro;

import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.retro.common.AuthInterceptor;
import retrofit2.Retrofit;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface IRetroWebApi extends IWebApi
{
    boolean isLogined();

    Retrofit getRetrofit();
    AuthInterceptor getAuthInterceptor();

    ICallExecuter getCallExecuter();
}
