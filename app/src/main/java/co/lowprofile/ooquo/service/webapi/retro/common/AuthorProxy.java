package co.lowprofile.ooquo.service.webapi.retro.common;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.SearchParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface AuthorProxy
{
    @POST("api/author")
    Call<NewAuthor> create(@Body NewAuthor newAuthor);

    @GET("api/author/{authorId}")
    Call<Author> get(@Path("authorId") int authorId);

    @POST("api/author/search")
    Call<List<Author>> search(@Body SearchParams searchParams);
}
