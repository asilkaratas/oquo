package co.lowprofile.ooquo.service.webapi.retro.common;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by asilkaratas on 1/18/16.
 */
public interface AccountProxy
{
    @POST("/token")
    Call<LoginResponse> login(@Body RequestBody loginData);

    @FormUrlEncoded
    @POST("api/account/register")
    Call<ResponseBody> register(@Field("Email") String username,
                                @Field("Password") String password,
                                @Field("ConfirmPassword") String confirmPassword);
}
