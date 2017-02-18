package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddAuthorResponseEvent
{
    private ServiceResponse<Author> response;
    public AddAuthorResponseEvent(ServiceResponse<Author> response)
    {
        this.response = response;
    }

    public ServiceResponse<Author> getResponse()
    {
        return response;
    }
}
