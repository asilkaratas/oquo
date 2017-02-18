package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetUserProfileResponseEvent
{
    private ServiceResponse<UserProfile> response;
    public GetUserProfileResponseEvent(ServiceResponse<UserProfile> response)
    {
        this.response = response;
    }

    public ServiceResponse<UserProfile> getResponse()
    {
        return response;
    }
}
