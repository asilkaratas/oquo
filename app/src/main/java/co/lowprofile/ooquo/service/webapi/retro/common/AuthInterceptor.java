package co.lowprofile.ooquo.service.webapi.retro.common;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asilkaratas on 1/19/16.
 */
public class AuthInterceptor implements Interceptor
{
    private AccessToken accessToken;

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder();
        requestBuilder.method(original.method(), original.body());

        if(accessToken != null)
        {
            requestBuilder.header("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    public AccessToken getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken)
    {
        this.accessToken = accessToken;
    }
}
