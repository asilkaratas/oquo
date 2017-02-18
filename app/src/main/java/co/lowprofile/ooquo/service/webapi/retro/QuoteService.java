package co.lowprofile.ooquo.service.webapi.retro;

import java.util.List;

import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.IBookService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.BookProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.QuoteProxy;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/16/16.
 */
public class QuoteService implements IQuoteService
{
    private IRetroWebApi retroWebApi;
    public QuoteService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    @Override
    public ServiceResponse<NewQuote> create(NewQuote newQuote)
    {
        QuoteProxy quoteProxy = retroWebApi.getRetrofit().create(QuoteProxy.class);

        Call<NewQuote> call = quoteProxy.create(newQuote);

        ServiceResponse<NewQuote> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<Quote> get(int quoteId)
    {
        QuoteProxy quoteProxy = retroWebApi.getRetrofit().create(QuoteProxy.class);

        Call<Quote> call = quoteProxy.get(quoteId);

        ServiceResponse<Quote> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<List<Quote>> getFeed()
    {
        QuoteProxy quoteProxy = retroWebApi.getRetrofit().create(QuoteProxy.class);

        Call<List<Quote>> call = quoteProxy.feed();

        ServiceResponse<List<Quote>> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }
}
