package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/31/16.
 */
public class GetLikeCountResponseEvent
{
    private ServiceResponse<Integer> response;
    private int quoteId;
    public GetLikeCountResponseEvent(int quoteId, ServiceResponse<Integer> response)
    {
        this.response = response;
        this.quoteId = quoteId;
    }

    public ServiceResponse<Integer> getResponse()
    {
        return response;
    }

    public int getQuoteId()
    {
        return quoteId;
    }
}
