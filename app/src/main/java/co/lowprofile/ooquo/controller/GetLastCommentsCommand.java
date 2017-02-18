package co.lowprofile.ooquo.controller;

import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.AddCommentResponseEvent;
import co.lowprofile.ooquo.event.GetLastCommentsResponseEvent;
import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.model.NewComment;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.ICommentService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetLastCommentsCommand extends Command
{
    private Quote quote;
    public GetLastCommentsCommand(Quote quote)
    {
        this.quote = quote;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        ICommentService commentService = webApi.getCommentService();
        ServiceResponse<List<Comment>> response = commentService.getLastComments(quote.getId());

        if(!response.hasError())
        {
            quote.getComments().clear();
            quote.getComments().addAll(response.getData());
        }

        GetLastCommentsResponseEvent event = new GetLastCommentsResponseEvent(quote.getId(), response);
        EventBus.getDefault().post(event);
    }
}
