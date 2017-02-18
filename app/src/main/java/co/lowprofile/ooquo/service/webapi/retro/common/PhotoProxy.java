package co.lowprofile.ooquo.service.webapi.retro.common;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by asilkaratas on 1/21/16.
 */
public interface PhotoProxy
{
    @Multipart
    @POST("api/user/{userId}/photo")
    Call<ResponseBody> uploadUserPhoto(@Path("userId") int userId,
                                       @Part("file\"; filename=\"picture.png ") RequestBody file);

    @Multipart
    @POST("api/author/{authorId}/photo")
    Call<ResponseBody> uploadAuthorPhoto(@Path("authorId") int authorId,
                                         @Part("file\"; filename=\"picture.png ") RequestBody file);


    @Multipart
    @POST("api/book/{bookId}/photo")
    Call<ResponseBody> uploadBookPhoto(@Path("bookId") int bookId,
                                       @Part("file\"; filename=\"picture.png ") RequestBody file);


    @Multipart
    @POST("api/quote/{quoteId}/photo")
    Call<ResponseBody> uploadQuotePhoto(@Path("quoteId") int quoteId,
                                        @Part("file\"; filename=\"picture.png ") RequestBody file);



}
