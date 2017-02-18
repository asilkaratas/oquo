package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddQuoteResponseEvent
{
    private ServiceResponse<Quote> response;
    public AddQuoteResponseEvent(ServiceResponse<Quote> response)
    {
        this.response = response;
    }

    public ServiceResponse<Quote> getResponse()
    {
        return response;
    }
}
