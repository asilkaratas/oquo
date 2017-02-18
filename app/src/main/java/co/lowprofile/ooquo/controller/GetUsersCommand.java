package co.lowprofile.ooquo.controller;

import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.GetUserProfileResponseEvent;
import co.lowprofile.ooquo.event.GetUsersResponseEvent;
import co.lowprofile.ooquo.model.GetUsers;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.IUserService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetUsersCommand extends Command
{
    private GetUsers getUsers;

    public GetUsersCommand(GetUsers getUsers)
    {
        this.getUsers = getUsers;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IUserService userService = webApi.getUserService();

        ServiceResponse<List<User>> response = null;
        switch (getUsers.getType())
        {
            case FOLLOWERS:
                response = userService.getFollowers(getUsers.getId());
                break;

            case FOLLOWING:
                response = userService.getFollowing(getUsers.getId());
                break;
        }


        GetUsersResponseEvent event = new GetUsersResponseEvent(response);
        EventBus.getDefault().post(event);
    }
}
