package co.lowprofile.ooquo.event;

import java.util.List;

import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class FeedResponseEvent
{
    private ServiceResponse<List<Quote>> response;
    public FeedResponseEvent(ServiceResponse<List<Quote>> response)
    {
        this.response = response;
    }

    public ServiceResponse<List<Quote>> getResponse()
    {
        return response;
    }
}
