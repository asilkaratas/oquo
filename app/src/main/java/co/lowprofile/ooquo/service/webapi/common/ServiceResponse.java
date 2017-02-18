package co.lowprofile.ooquo.service.webapi.common;

/**
 * Created by asilkaratas on 12/11/15.
 */
public class ServiceResponse<T>
{
    private T data = null;
    private ServiceError error = null;
    public ServiceResponse(T data)
    {
        this.data = data;
    }

    public T getData()
    {
        return data;
    }

    public void setError(ServiceError error)
    {
        this.error = error;
    }

    public ServiceError getError()
    {
        return error;
    }

    public boolean hasError()
    {
        return error != null;
    }
}
