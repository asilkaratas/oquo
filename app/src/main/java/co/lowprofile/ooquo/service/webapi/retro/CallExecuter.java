package co.lowprofile.ooquo.service.webapi.retro;

import java.io.IOException;

import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by asilkaratas on 1/19/16.
 */
public class CallExecuter implements ICallExecuter
{
    public <T> ServiceResponse<T> execute(Call<T> call)
    {
        Response<T> response = null;
        ServiceError error = null;
        T body = null;

        try
        {
            response = call.execute();
            body = response.body();

        } catch (IOException e)
        {
            error = new ServiceError("Server is not accessible!");
        }

        if(response != null && !response.isSuccess())
        {
            error = new ServiceError(response.message());
        }

        ServiceResponse<T> serviceResponse = new ServiceResponse<T>(body);
        serviceResponse.setError(error);

        return serviceResponse;
    }
}
