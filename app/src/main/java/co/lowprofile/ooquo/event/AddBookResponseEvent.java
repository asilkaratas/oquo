package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddBookResponseEvent
{
    private ServiceResponse<Book> response;
    public AddBookResponseEvent(ServiceResponse<Book> response)
    {
        this.response = response;
    }

    public ServiceResponse<Book> getResponse()
    {
        return response;
    }
}
