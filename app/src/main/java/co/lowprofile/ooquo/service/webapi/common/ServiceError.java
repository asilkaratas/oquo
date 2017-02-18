package co.lowprofile.ooquo.service.webapi.common;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class ServiceError
{
    private String message;

    public ServiceError(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
