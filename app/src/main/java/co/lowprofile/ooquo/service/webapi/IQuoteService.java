package co.lowprofile.ooquo.service.webapi;

import java.util.List;

import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface IQuoteService
{
    ServiceResponse<NewQuote> create(NewQuote newQuote);
    ServiceResponse<Quote> get(int quoteId);
    ServiceResponse<List<Quote>> getFeed();
}
