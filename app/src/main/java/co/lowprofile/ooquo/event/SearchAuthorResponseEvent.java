package co.lowprofile.ooquo.event;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class SearchAuthorResponseEvent
{
    private ServiceResponse<List<Author>> response;
    public SearchAuthorResponseEvent(ServiceResponse<List<Author>> response)
    {
        this.response = response;
    }

    public ServiceResponse<List<Author>> getResponse()
    {
        return response;
    }
}
