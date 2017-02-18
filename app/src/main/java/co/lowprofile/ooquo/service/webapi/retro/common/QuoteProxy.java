package co.lowprofile.ooquo.service.webapi.retro.common;

import java.util.List;

import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.model.SearchParams;
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
public interface QuoteProxy
{
    @POST("api/quote")
    Call<NewQuote> create(@Body NewQuote newQuote);

    @GET("api/quote/{quoteId}")
    Call<Quote> get(@Path("quoteId") int quoteId);

    @GET("api/feed")
    Call<List<Quote>> feed();
}
