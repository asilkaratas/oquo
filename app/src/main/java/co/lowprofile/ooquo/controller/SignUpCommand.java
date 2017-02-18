package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.SignUpResponseEvent;
import co.lowprofile.ooquo.model.NewUser;
import co.lowprofile.ooquo.model.SignUp;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.IAccountService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IUserService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/11/16.
 */
public class SignUpCommand extends Command
{
    private SignUp signUp;
    public SignUpCommand(SignUp signUp)
    {
        this.signUp = signUp;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IAccountService accountService = webApi.getAccountService();
        IUserService userService = webApi.getUserService();
        IPhotoService photoService = webApi.getPhotoService();

        ServiceResponse<Boolean> registerResponse = accountService.register(signUp.getEmail(),
                signUp.getPassword(), signUp.getConfirmPassword());

        ServiceError error = null;

        if(!registerResponse.hasError())
        {
            ServiceResponse<Boolean> loginResponse = accountService.login(signUp.getEmail(),
                    signUp.getPassword());
            if(!loginResponse.hasError())
            {
                ServiceResponse<User> getMeResponse = userService.getMe();
                if(!getMeResponse.hasError())
                {
                    User user = getMeResponse.getData();

                    NewUser newUser = new NewUser();
                    newUser.setId(user.getId());
                    newUser.setFirstName(signUp.getFirstName());
                    newUser.setLastName(signUp.getLastName());

                    ServiceResponse<Boolean> updateResponse = userService.update(newUser);
                    if(!updateResponse.hasError())
                    {
                        getMeResponse = userService.getMe();
                        if(!getMeResponse.hasError())
                        {
                            user = getMeResponse.getData();
                            OoquoApplication.getAppModel().setCurrentUser(user);

                            if(signUp.getBitmap() != null)
                            {
                                ServiceResponse<Boolean> response = photoService.uploadUserPhotoWithBitmap(user.getId(), signUp.getBitmap());

                                if(response.hasError())
                                {
                                    error = response.getError();
                                }
                            }
                        }
                        else
                        {
                            error = getMeResponse.getError();
                        }
                    }
                    else
                    {
                        error = updateResponse.getError();
                    }
                }
                else
                {
                    error = getMeResponse.getError();
                }
            }
            else
            {
                error = loginResponse.getError();
            }
        }
        else
        {
            error = registerResponse.getError();
        }

        ServiceResponse<Boolean> signUpResponse = new ServiceResponse<>(error == null);
        signUpResponse.setError(error);

        SignUpResponseEvent event = new SignUpResponseEvent(signUpResponse);

        EventBus.getDefault().post(event);
    }
}
