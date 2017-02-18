package co.lowprofile.ooquo.service.webapi.retro;

import java.util.List;

import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.model.Count;
import co.lowprofile.ooquo.model.NewComment;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.ICommentService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.CommentProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.LikeProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.QuoteProxy;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/16/16.
 */
public class CommentService implements ICommentService
{
    private IRetroWebApi retroWebApi;
    public CommentService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    @Override
    public ServiceResponse<NewComment> create(NewComment newComment)
    {
        CommentProxy commentProxy = retroWebApi.getRetrofit().create(CommentProxy.class);

        Call<NewComment> call = commentProxy.create(newComment);

        ServiceResponse<NewComment> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<List<Comment>> getLastComments(int quoteId)
    {
        CommentProxy commentProxy = retroWebApi.getRetrofit().create(CommentProxy.class);

        Call<List<Comment>> call = commentProxy.getLastComments(quoteId);

        ServiceResponse<List<Comment>> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<Integer> getCommentCount(int quoteId)
    {
        CommentProxy commentProxy = retroWebApi.getRetrofit().create(CommentProxy.class);

        Call<Count> call = commentProxy.getCommentCount(quoteId);

        ServiceResponse<Count> responseBody = retroWebApi.getCallExecuter().execute(call);

        ServiceResponse<Integer> response = new ServiceResponse<>(responseBody.getData().getCount());
        response.setError(responseBody.getError());

        return response;
    }

}
