package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.FollowResponseEvent;
import co.lowprofile.ooquo.event.UnfollowResponseEvent;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.IFollowService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class UnfollowCommand extends Command
{
    private UserProfile userProfile;
    public UnfollowCommand(UserProfile userProfile)
    {
        this.userProfile = userProfile;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IFollowService service = webApi.getFollowService();
        ServiceResponse<Boolean> response = service.unfollow(userProfile.getId());

        userProfile.setIsFollowed(false);

        UnfollowResponseEvent event = new UnfollowResponseEvent(userProfile.getId());
        EventBus.getDefault().post(event);
    }
}
