package co.lowprofile.ooquo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;

import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by asilkaratas on 1/21/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
//@Config(constants = BuildConfig.class)
@Config(sdk = 18, constants = BuildConfig.class)
public class PhotoServiceTest
{
    private IPhotoService photoService;

    @Before
    public void init()
    {
        OoquoApplication application = new OoquoApplication();
        IWebApi webApi = application.getWebApi();
        photoService = webApi.getPhotoService();
    }

    @Test
    public void loadLocalFile()
    {
        File file = new File("src/main/assets/images/adult_small_icon.png");
        assertNotNull(file.exists());
    }

    @Test
    public void uploadUserPhoto()
    {
        int userId = 1;
        File file = new File("src/main/assets/images/adult_small_icon.png");
        ServiceResponse<Boolean> response = photoService.uploadUserPhoto(userId, file);

        assertTrue(response.getData());
    }

}
