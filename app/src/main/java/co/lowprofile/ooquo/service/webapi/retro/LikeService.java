package co.lowprofile.ooquo.service.webapi.retro;

import java.util.List;

import co.lowprofile.ooquo.model.Count;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.ILikeService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.LikeProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.QuoteProxy;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/16/16.
 */
public class LikeService implements ILikeService
{
    private IRetroWebApi retroWebApi;
    public LikeService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    @Override
    public ServiceResponse<Boolean> like(int quoteId)
    {
        LikeProxy likeProxy = retroWebApi.getRetrofit().create(LikeProxy.class);

        Call<ResponseBody> call = likeProxy.like(quoteId);

        ICallExecuter callExecuter = retroWebApi.getCallExecuter();
        ServiceResponse<ResponseBody> responseBody = callExecuter.execute(call);

        ServiceResponse<Boolean> response = new ServiceResponse<>(!responseBody.hasError());
        response.setError(responseBody.getError());

        return response;
    }

    @Override
    public ServiceResponse<Boolean> dislike(int quoteId)
    {
        LikeProxy likeProxy = retroWebApi.getRetrofit().create(LikeProxy.class);

        Call<ResponseBody> call = likeProxy.dislike(quoteId);

        ICallExecuter callExecuter = retroWebApi.getCallExecuter();
        ServiceResponse<ResponseBody> responseBody = callExecuter.execute(call);

        ServiceResponse<Boolean> response = new ServiceResponse<>(!responseBody.hasError());
        response.setError(responseBody.getError());

        return response;
    }

    @Override
    public ServiceResponse<Integer> getLikeCount(int quoteId)
    {
        LikeProxy likeProxy = retroWebApi.getRetrofit().create(LikeProxy.class);

        Call<Count> call = likeProxy.getLikeCount(quoteId);

        ServiceResponse<Count> responseBody = retroWebApi.getCallExecuter().execute(call);

        ServiceResponse<Integer> response = new ServiceResponse<>(responseBody.getData().getCount());
        response.setError(responseBody.getError());

        return response;
    }
}
