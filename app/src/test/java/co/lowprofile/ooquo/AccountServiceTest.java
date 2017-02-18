package co.lowprofile.ooquo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;

import co.lowprofile.ooquo.model.NewUser;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.IAccountService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IUserService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by asilkaratas on 1/20/16.
 */


@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class)
@Config(sdk = 18, constants = BuildConfig.class)
public class AccountServiceTest
{
    private String username = "t@t.com";
    private String password = "P@ssword1!";

    private IAccountService accountService;
    private IUserService userService;
    private IPhotoService photoService;

    @Before
    public void init()
    {
        OoquoApplication application = new OoquoApplication();
        IWebApi webApi = application.getWebApi();
        accountService = webApi.getAccountService();
        userService = webApi.getUserService();
        photoService = webApi.getPhotoService();
    }

    @Test
    public void login_shouldSucceed()
    {
        //Arrange

        //Act
        ServiceResponse<Boolean> response = accountService.login(username, password);

        //Assert
        assertTrue(response.getData());
    }

    @Test
    public void getMe_shouldSucceed()
    {
        //Arrange

        //Act
        ServiceResponse<Boolean> response = accountService.login(username, password);
        ServiceResponse<User> responseGetMe = userService.getMe();

        //Assert
        assertTrue(response.getData());
        assertNotNull(responseGetMe);
        assertNotNull(responseGetMe.getData());
    }

    @Test
    public void signup_shouldSucceed()
    {
        //Arrange
        long start_time = System.currentTimeMillis();

        String firstName = "Asil" + start_time;
        String lastName = "Karatas" + start_time;

        //Act
        ServiceResponse<Boolean> registerResponse = accountService.register(username, password, password);
        ServiceResponse<Boolean> loginResponse = accountService.login(username, password);
        ServiceResponse<User> getMeResponse = userService.getMe();

        User user = getMeResponse.getData();
        NewUser newUser = new NewUser();
        newUser.setId(user.getId());
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);

        ServiceResponse<Boolean> updateResponse = userService.update(newUser);

        getMeResponse = userService.getMe();

        user = getMeResponse.getData();
        assertTrue(user.getFirstName().equals(firstName));
        assertTrue(user.getLastName().equals(lastName));


        File file = new File("src/main/assets/images/adult_small_icon.png");
        ServiceResponse<Boolean> response = photoService.uploadUserPhoto(user.getId(), file);

        assertTrue(response.getData());

    }




}
