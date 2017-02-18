package co.lowprofile.ooquo.service.webapi.retro.common;

import java.util.List;

import co.lowprofile.ooquo.model.Count;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface LikeProxy
{
    @POST("api/quote/{quoteId}/like")
    Call<ResponseBody> like(@Path("quoteId") int quoteId);

    @DELETE("api/quote/{quoteId}/like")
    Call<ResponseBody> dislike(@Path("quoteId") int quoteId);

    @GET("api/quote/{quoteId}/like/count")
    Call<Count> getLikeCount(@Path("quoteId") int quoteId);
}
