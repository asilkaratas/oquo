package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.GetBookProfileResponseEvent;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.service.webapi.IBookService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetBookProfileCommand extends Command
{
    private int bookId;

    public GetBookProfileCommand(int bookId)
    {
        this.bookId = bookId;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IQuoteService quoteService = webApi.getQuoteService();
        IBookService bookService = webApi.getBookService();
        ServiceResponse<BookProfile> response = bookService.getBookProfile(bookId);

        GetBookProfileResponseEvent event = new GetBookProfileResponseEvent(response);
        EventBus.getDefault().post(event);
    }
}
