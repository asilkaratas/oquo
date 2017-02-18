package co.lowprofile.ooquo.controller;

import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.FeedResponseEvent;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetFeedCommand extends Command
{
    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IQuoteService quoteService = webApi.getQuoteService();
        ServiceResponse<List<Quote>> feedResponse = quoteService.getFeed();


        FeedResponseEvent event = new FeedResponseEvent(feedResponse);
        EventBus.getDefault().post(event);
    }
}
