package co.lowprofile.ooquo.event;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class SearchBookResponseEvent
{
    private ServiceResponse<List<Book>> response;
    public SearchBookResponseEvent(ServiceResponse<List<Book>> response)
    {
        this.response = response;
    }

    public ServiceResponse<List<Book>> getResponse()
    {
        return response;
    }
}
