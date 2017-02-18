package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.GetCommentCountResponseEvent;
import co.lowprofile.ooquo.event.GetLikeCountResponseEvent;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.ICommentService;
import co.lowprofile.ooquo.service.webapi.ILikeService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetCommentCountCommand extends Command
{
    private Quote quote;
    public GetCommentCountCommand(Quote quote)
    {
        this.quote = quote;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        ICommentService service = webApi.getCommentService();
        ServiceResponse<Integer> response = service.getCommentCount(quote.getId());

        if(!response.hasError())
        {
            quote.setCommentCount(response.getData());
        }

        GetCommentCountResponseEvent event = new GetCommentCountResponseEvent(quote.getId(), response);
        EventBus.getDefault().post(event);
    }
}
