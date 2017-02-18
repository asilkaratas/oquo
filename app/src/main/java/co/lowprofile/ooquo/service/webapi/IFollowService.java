package co.lowprofile.ooquo.service.webapi;

import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface IFollowService
{
    ServiceResponse<Boolean> follow(int userId);
    ServiceResponse<Boolean> unfollow(int userId);
    ServiceResponse<Integer> getFollowerCount(int userId);
}
