package co.lowprofile.ooquo.service.webapi.retro;

import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface ICallExecuter
{
    <T> ServiceResponse<T> execute(Call<T> call);
}
