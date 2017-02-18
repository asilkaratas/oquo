package co.lowprofile.ooquo.controller;

import android.graphics.Bitmap;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.AddAuthorResponseEvent;
import co.lowprofile.ooquo.event.GetUserProfileResponseEvent;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IUserService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetUserProfileCommand extends Command
{
    private int userId;

    public GetUserProfileCommand(int userId)
    {
        this.userId = userId;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IUserService userService = webApi.getUserService();
        ServiceResponse<UserProfile> response = userService.getUserProfile(userId);


        GetUserProfileResponseEvent event = new GetUserProfileResponseEvent(response);
        EventBus.getDefault().post(event);
    }
}
