package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.model.NewComment;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddCommentResponseEvent
{
    private ServiceResponse<Comment> response;
    private int quoteId;

    public AddCommentResponseEvent(int quoteId, ServiceResponse<Comment> response)
    {
        this.quoteId = quoteId;
        this.response = response;
    }

    public ServiceResponse<Comment> getResponse()
    {
        return response;
    }

    public int getQuoteId()
    {
        return quoteId;
    }
}
