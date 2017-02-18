package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.GetLikeCountResponseEvent;
import co.lowprofile.ooquo.event.LikeResponseEvent;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.ILikeService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetLikeCountCommand extends Command
{
    private Quote quote;
    public GetLikeCountCommand(Quote quote)
    {
        this.quote = quote;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        ILikeService likeService = webApi.getLikeService();
        ServiceResponse<Integer> response = likeService.getLikeCount(quote.getId());

        if(!response.hasError())
        {
            quote.setLikeCount(response.getData());
        }

        GetLikeCountResponseEvent event = new GetLikeCountResponseEvent(quote.getId(), response);
        EventBus.getDefault().post(event);
    }
}
