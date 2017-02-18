package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.GetFollowerCountResponseEvent;
import co.lowprofile.ooquo.event.UnfollowResponseEvent;
import co.lowprofile.ooquo.model.Count;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.IFollowService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class GetFollowerCountCommand extends Command
{
    private UserProfile userProfile;
    public GetFollowerCountCommand(UserProfile userProfile)
    {
        this.userProfile = userProfile;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IFollowService service = webApi.getFollowService();
        ServiceResponse<Integer> response = service.getFollowerCount(userProfile.getId());

        userProfile.setFollowerCount(response.getData());

        GetFollowerCountResponseEvent event = new GetFollowerCountResponseEvent(userProfile.getId(), response);
        EventBus.getDefault().post(event);
    }
}
