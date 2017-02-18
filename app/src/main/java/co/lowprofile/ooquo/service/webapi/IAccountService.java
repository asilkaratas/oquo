package co.lowprofile.ooquo.service.webapi;

import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface IAccountService
{
    ServiceResponse<Boolean> login(String username, String password);
    ServiceResponse<Boolean> register(String username, String password, String repassword);
}
