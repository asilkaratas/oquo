package co.lowprofile.ooquo.event;

import java.util.List;

import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/31/16.
 */
public class GetLastCommentsResponseEvent
{
    private ServiceResponse<List<Comment>> response;
    private int quoteId;
    public GetLastCommentsResponseEvent(int quoteId, ServiceResponse<List<Comment>> response)
    {
        this.response = response;
        this.quoteId = quoteId;
    }

    public ServiceResponse<List<Comment>> getResponse()
    {
        return response;
    }

    public int getQuoteId()
    {
        return quoteId;
    }
}
