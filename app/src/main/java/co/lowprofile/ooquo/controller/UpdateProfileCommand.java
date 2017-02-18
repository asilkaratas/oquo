package co.lowprofile.ooquo.controller;

import android.graphics.Bitmap;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.SignUpResponseEvent;
import co.lowprofile.ooquo.event.UpdateProfileResponseEvent;
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
public class UpdateProfileCommand extends Command
{
    private NewUser newUser;
    private Bitmap bitmap;
    public UpdateProfileCommand(NewUser newUser, Bitmap bitmap)
    {
        this.newUser = newUser;
        this.bitmap = bitmap;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IUserService userService = webApi.getUserService();
        IPhotoService photoService = webApi.getPhotoService();

        ServiceResponse<Boolean> updateResponse = userService.update(newUser);

        ServiceError error = null;

        if(!updateResponse.hasError())
        {
            if(bitmap != null)
            {
                ServiceResponse<Boolean> response = photoService.uploadUserPhotoWithBitmap(newUser.getId(), bitmap);

                if(response.hasError())
                {
                    error = response.getError();
                }
            }
        }
        else
        {
            error = updateResponse.getError();
        }

        if(error == null)
        {
            ServiceResponse<User> getMeResponse = userService.getMe();
            if(!getMeResponse.hasError())
            {
                OoquoApplication.getAppModel().setCurrentUser(getMeResponse.getData());
            }
            else
            {
                error = getMeResponse.getError();
            }
        }

        ServiceResponse<Boolean> response = new ServiceResponse<>(error == null);
        response.setError(error);

        UpdateProfileResponseEvent event = new UpdateProfileResponseEvent(response);
        EventBus.getDefault().post(event);
    }
}
