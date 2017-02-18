package co.lowprofile.ooquo.service.webapi.retro;

import co.lowprofile.ooquo.service.webapi.IAccountService;
import co.lowprofile.ooquo.service.webapi.common.*;
import co.lowprofile.ooquo.service.webapi.retro.common.AccessToken;
import co.lowprofile.ooquo.service.webapi.retro.common.AccountProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.LoginResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/14/16.
 */
public class AccountService implements IAccountService
{
    private IRetroWebApi retroWebApi;
    public AccountService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    public ServiceResponse<Boolean> login(String username, String password)
    {
        String loginData = "grant_type=password&username=" + username + "&password=" + password;

        AccountProxy accountProxy = retroWebApi.getRetrofit().create(AccountProxy.class);

        RequestBody requestBody = RequestBody.create(MediaType.parse("plain/text"), loginData);

        Call<LoginResponse> call = accountProxy.login(requestBody);
        ServiceResponse<LoginResponse> response = retroWebApi.getCallExecuter().execute(call);

        if(!response.hasError())
        {
            AccessToken accessToken = new AccessToken("Bearer", response.getData().access_token);
            retroWebApi.getAuthInterceptor().setAccessToken(accessToken);
        }

        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<>(!response.hasError());
        serviceResponse.setError(response.getError());
        return serviceResponse;
    }

    @Override
    public ServiceResponse<Boolean> register(String username, String password, String repassword)
    {
        AccountProxy accountProxy = retroWebApi.getRetrofit().create(AccountProxy.class);

        Call<ResponseBody> call = accountProxy.register(username, password, repassword);
        ServiceResponse<ResponseBody> response = retroWebApi.getCallExecuter().execute(call);

        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<>(!response.hasError());
        serviceResponse.setError(response.getError());
        return serviceResponse;
    }


}
