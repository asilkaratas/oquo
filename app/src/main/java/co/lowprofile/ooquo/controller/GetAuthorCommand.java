package co.lowprofile.ooquo.controller;

import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.GetAuthorResponseEvent;
import co.lowprofile.ooquo.event.SearchAuthorResponseEvent;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetAuthorCommand extends Command
{
    private int authorId;
    public GetAuthorCommand(int authorId)
    {
        this.authorId = authorId;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IAuthorService authorService = webApi.getAuthorService();

        ServiceResponse<Author> searchResponse = authorService.get(authorId);

        GetAuthorResponseEvent event = new GetAuthorResponseEvent(searchResponse);
        EventBus.getDefault().post(event);
    }
}
