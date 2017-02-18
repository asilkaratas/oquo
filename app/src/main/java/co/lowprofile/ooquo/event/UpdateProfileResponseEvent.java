package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/11/16.
 */
public class UpdateProfileResponseEvent
{
    private ServiceResponse<Boolean> response;
    public UpdateProfileResponseEvent(ServiceResponse<Boolean> response)
    {
        this.response = response;
    }

    public ServiceResponse<Boolean> getResponse()
    {
        return response;
    }
}
