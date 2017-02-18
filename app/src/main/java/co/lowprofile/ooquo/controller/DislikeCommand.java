package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.DislikeResponseEvent;
import co.lowprofile.ooquo.event.LikeResponseEvent;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.ILikeService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class DislikeCommand extends Command
{
    private Quote quote;
    public DislikeCommand(Quote quote)
    {
        this.quote = quote;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        ILikeService likeService = webApi.getLikeService();
        ServiceResponse<Boolean> likeResponse = likeService.dislike(quote.getId());

        quote.setIsLiked(false);

        DislikeResponseEvent event = new DislikeResponseEvent(quote.getId());
        EventBus.getDefault().post(event);
    }
}
