package co.lowprofile.ooquo.service.webapi.retro.common;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.model.SearchParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface BookProxy
{
    @POST("api/book")
    Call<NewBook> create(@Body NewBook newBook);

    @GET("api/book/{bookId}")
    Call<Book> get(@Path("bookId") int bookId);

    @GET("api/book/{bookId}/profile")
    Call<BookProfile> getBookProfile(@Path("bookId") int bookId);

    @POST("api/book/search")
    Call<List<Book>> search(@Body SearchParams searchParams);
}
