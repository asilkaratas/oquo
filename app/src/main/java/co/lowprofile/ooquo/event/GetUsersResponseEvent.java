package co.lowprofile.ooquo.event;

import java.util.List;

import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetUsersResponseEvent
{
    private ServiceResponse<List<User>> response;
    public GetUsersResponseEvent(ServiceResponse<List<User>> response)
    {
        this.response = response;
    }

    public ServiceResponse<List<User>> getResponse()
    {
        return response;
    }
}
