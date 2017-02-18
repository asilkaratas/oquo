package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/11/16.
 */
public class SignUpResponseEvent
{
    private ServiceResponse<Boolean> response;
    public SignUpResponseEvent(ServiceResponse<Boolean> response)
    {
        this.response = response;
    }

    public ServiceResponse<Boolean> getResponse()
    {
        return response;
    }
}
