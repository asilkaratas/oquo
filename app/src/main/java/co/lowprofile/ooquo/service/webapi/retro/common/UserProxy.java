package co.lowprofile.ooquo.service.webapi.retro.common;

import java.util.List;

import co.lowprofile.ooquo.model.NewUser;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.model.UserProfile;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface UserProxy
{
    @GET("api/user/loginId")
    Call<User> getMe();

    @PUT("api/user/{userId}")
    Call<ResponseBody> update(@Path("userId") int userId,
                              @Body NewUser newUser);

    @GET("api/user/{userId}")
    Call<UserProfile> getUserProfile(@Path("userId") int userId);

    @GET("api/user/{userId}/following")
    Call<List<User>> getFollowing(@Path("userId") int userId);

    @GET("api/user/{userId}/follower")
    Call<List<User>> getFollowers(@Path("userId") int userId);
}
