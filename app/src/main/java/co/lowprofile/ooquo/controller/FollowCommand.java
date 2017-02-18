package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.FollowResponseEvent;
import co.lowprofile.ooquo.event.LikeResponseEvent;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.IFollowService;
import co.lowprofile.ooquo.service.webapi.ILikeService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class FollowCommand extends Command
{
    private UserProfile userProfile;
    public FollowCommand(UserProfile userProfile)
    {
        this.userProfile = userProfile;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IFollowService service = webApi.getFollowService();
        ServiceResponse<Boolean> response = service.follow(userProfile.getId());

        userProfile.setIsFollowed(true);

        FollowResponseEvent event = new FollowResponseEvent(userProfile.getId());
        EventBus.getDefault().post(event);
    }
}
