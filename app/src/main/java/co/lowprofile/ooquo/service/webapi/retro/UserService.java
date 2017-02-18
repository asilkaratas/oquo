package co.lowprofile.ooquo.service.webapi.retro;

import java.util.List;

import co.lowprofile.ooquo.model.NewUser;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.IUserService;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.UserProxy;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/19/16.
 */
public class UserService implements IUserService
{
    private IRetroWebApi retroWebApi;
    public UserService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    @Override
    public ServiceResponse<User> getMe()
    {
        UserProxy userProxy = retroWebApi.getRetrofit().create(UserProxy.class);
        return retroWebApi.getCallExecuter().execute(userProxy.getMe());
    }

    @Override
    public ServiceResponse<Boolean> update(NewUser newUser)
    {
        UserProxy userProxy = retroWebApi.getRetrofit().create(UserProxy.class);
        Call<ResponseBody> call = userProxy.update(newUser.getId(), newUser);

        ServiceResponse<ResponseBody> response = retroWebApi.getCallExecuter().execute(call);

        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<>(!response.hasError());
        serviceResponse.setError(response.getError());
        return serviceResponse;
    }

    @Override
    public ServiceResponse<UserProfile> getUserProfile(int userId)
    {
        UserProxy userProxy = retroWebApi.getRetrofit().create(UserProxy.class);
        Call<UserProfile> call = userProxy.getUserProfile(userId);

        ServiceResponse<UserProfile> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<List<User>> getFollowers(int userId)
    {
        UserProxy userProxy = retroWebApi.getRetrofit().create(UserProxy.class);
        Call<List<User>> call = userProxy.getFollowers(userId);

        ServiceResponse<List<User>> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<List<User>> getFollowing(int userId)
    {
        UserProxy userProxy = retroWebApi.getRetrofit().create(UserProxy.class);
        Call<List<User>> call = userProxy.getFollowing(userId);

        ServiceResponse<List<User>> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }
}
