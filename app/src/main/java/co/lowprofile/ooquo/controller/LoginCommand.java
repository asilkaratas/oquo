package co.lowprofile.ooquo.controller;


import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.LoginResponseEvent;
import co.lowprofile.ooquo.model.Login;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.IAccountService;
import co.lowprofile.ooquo.service.webapi.IUserService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;


/**
 * Created by asilkaratas on 1/11/16.
 */
public class LoginCommand extends Command
{
    private Login login;
    public LoginCommand(Login login)
    {
        this.login = login;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IAccountService accountService = webApi.getAccountService();
        ServiceResponse<Boolean> response = accountService.login(login.getEmail(), login.getPassword());

        ServiceError error = null;
        if(!response.hasError())
        {
            IUserService userService = webApi.getUserService();
            ServiceResponse<User> userResponse = userService.getMe();

            if(!userResponse.hasError())
            {
                OoquoApplication.getAppModel().setCurrentUser(userResponse.getData());
            }
            else
            {
                error = userResponse.getError();
            }
        }
        else
        {
            error = response.getError();
        }


        ServiceResponse<Boolean> loginResponse = new ServiceResponse<>(error == null);
        loginResponse.setError(error);

        LoginResponseEvent event = new LoginResponseEvent(loginResponse);
        EventBus.getDefault().post(event);
    }
}
