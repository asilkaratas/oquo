package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetBookProfileResponseEvent
{
    private ServiceResponse<BookProfile> response;
    public GetBookProfileResponseEvent(ServiceResponse<BookProfile> response)
    {
        this.response = response;
    }

    public ServiceResponse<BookProfile> getResponse()
    {
        return response;
    }
}
