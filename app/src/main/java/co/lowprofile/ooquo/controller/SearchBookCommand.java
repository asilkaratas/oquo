package co.lowprofile.ooquo.controller;

import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.SearchAuthorResponseEvent;
import co.lowprofile.ooquo.event.SearchBookResponseEvent;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.IBookService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class SearchBookCommand extends Command
{
    private String key;
    public SearchBookCommand(String key)
    {
        this.key = key;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IBookService bookService = webApi.getBookService();

        SearchParams searchParams = new SearchParams(key, 10);
        ServiceResponse<List<Book>> searchResponse = bookService.search(searchParams);

        SearchBookResponseEvent event = new SearchBookResponseEvent(searchResponse);
        EventBus.getDefault().post(event);
    }
}
