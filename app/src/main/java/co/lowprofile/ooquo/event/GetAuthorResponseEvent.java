package co.lowprofile.ooquo.event;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetAuthorResponseEvent
{
    private ServiceResponse<Author> response;
    public GetAuthorResponseEvent(ServiceResponse<Author> response)
    {
        this.response = response;
    }

    public ServiceResponse<Author> getResponse()
    {
        return response;
    }
}
