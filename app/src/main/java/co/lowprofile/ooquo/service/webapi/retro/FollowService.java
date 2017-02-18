package co.lowprofile.ooquo.service.webapi.retro;

import co.lowprofile.ooquo.model.Count;
import co.lowprofile.ooquo.service.webapi.IFollowService;
import co.lowprofile.ooquo.service.webapi.ILikeService;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.FollowProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.LikeProxy;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/16/16.
 */
public class FollowService implements IFollowService
{
    private IRetroWebApi retroWebApi;
    public FollowService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    @Override
    public ServiceResponse<Boolean> follow(int userId)
    {
        FollowProxy followProxy = retroWebApi.getRetrofit().create(FollowProxy.class);

        Call<ResponseBody> call = followProxy.follow(userId);

        ICallExecuter callExecuter = retroWebApi.getCallExecuter();
        ServiceResponse<ResponseBody> responseBody = callExecuter.execute(call);

        ServiceResponse<Boolean> response = new ServiceResponse<>(!responseBody.hasError());
        response.setError(responseBody.getError());

        return response;
    }

    @Override
    public ServiceResponse<Boolean> unfollow(int userId)
    {
        FollowProxy followProxy = retroWebApi.getRetrofit().create(FollowProxy.class);

        Call<ResponseBody> call = followProxy.unfollow(userId);

        ICallExecuter callExecuter = retroWebApi.getCallExecuter();
        ServiceResponse<ResponseBody> responseBody = callExecuter.execute(call);

        ServiceResponse<Boolean> response = new ServiceResponse<>(!responseBody.hasError());
        response.setError(responseBody.getError());

        return response;
    }

    @Override
    public ServiceResponse<Integer> getFollowerCount(int userId)
    {
        FollowProxy followProxy = retroWebApi.getRetrofit().create(FollowProxy.class);

        Call<Count> call = followProxy.getFollowerCount(userId);

        ServiceResponse<Count> responseBody = retroWebApi.getCallExecuter().execute(call);

        ServiceResponse<Integer> response = new ServiceResponse<>(responseBody.getData().getCount());
        response.setError(responseBody.getError());

        return response;
    }
}
