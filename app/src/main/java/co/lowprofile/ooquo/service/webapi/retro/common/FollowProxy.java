package co.lowprofile.ooquo.service.webapi.retro.common;

import co.lowprofile.ooquo.model.Count;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface FollowProxy
{
    @POST("api/user/{userId}/follow")
    Call<ResponseBody> follow(@Path("userId") int userId);

    @POST("api/user/{userId}/unfollow")
    Call<ResponseBody> unfollow(@Path("userId") int userId);

    @GET("api/user/{userId}/follower/count")
    Call<Count> getFollowerCount(@Path("userId") int userId);
}
