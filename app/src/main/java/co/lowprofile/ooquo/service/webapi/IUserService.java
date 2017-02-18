package co.lowprofile.ooquo.service.webapi;

import java.util.List;

import co.lowprofile.ooquo.model.NewUser;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/19/16.
 */
public interface IUserService
{
    ServiceResponse<User> getMe();
    ServiceResponse<Boolean> update(NewUser newUser);
    ServiceResponse<UserProfile> getUserProfile(int userId);
    ServiceResponse<List<User>> getFollowers(int userId);
    ServiceResponse<List<User>> getFollowing(int userId);
}
