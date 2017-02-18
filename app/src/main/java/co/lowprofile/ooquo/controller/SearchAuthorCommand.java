package co.lowprofile.ooquo.controller;

import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.SearchAuthorResponseEvent;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class SearchAuthorCommand extends Command
{
    private String key;
    public SearchAuthorCommand(String key)
    {
        this.key = key;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IAuthorService authorService = webApi.getAuthorService();

        SearchParams searchParams = new SearchParams(key, 10);
        ServiceResponse<List<Author>> searchResponse = authorService.search(searchParams);

        SearchAuthorResponseEvent event = new SearchAuthorResponseEvent(searchResponse);
        EventBus.getDefault().post(event);
    }
}
