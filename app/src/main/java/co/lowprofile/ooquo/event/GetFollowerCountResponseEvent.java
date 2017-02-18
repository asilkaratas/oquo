package co.lowprofile.ooquo.event;

import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/31/16.
 */
public class GetFollowerCountResponseEvent
{
    private ServiceResponse<Integer> response;
    private int userId;
    public GetFollowerCountResponseEvent(int userId, ServiceResponse<Integer> response)
    {
        this.response = response;
        this.userId = userId;
    }

    public ServiceResponse<Integer> getResponse()
    {
        return response;
    }

    public int getUserId()
    {
        return userId;
    }
}
