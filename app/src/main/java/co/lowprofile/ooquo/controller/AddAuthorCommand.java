package co.lowprofile.ooquo.controller;

import android.graphics.Bitmap;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.AddAuthorResponseEvent;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddAuthorCommand extends Command
{
    private NewAuthor newAuthor;
    private Bitmap bitmap;

    public AddAuthorCommand(NewAuthor newAuthor, Bitmap bitmap)
    {
        this.newAuthor = newAuthor;
        this.bitmap = bitmap;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IAuthorService authorService = webApi.getAuthorService();
        IPhotoService photoService = webApi.getPhotoService();
        ServiceResponse<NewAuthor> addAuthorResponse = authorService.create(newAuthor);

        ServiceError error = null;
        Author author = null;
        if(!addAuthorResponse.hasError())
        {
            newAuthor = addAuthorResponse.getData();
            if(bitmap != null)
            {
                ServiceResponse<Boolean> photoResponse = photoService.uploadAuthorPhotoWithBitmap(newAuthor.getId(), bitmap);
                if(photoResponse.hasError())
                {
                    error = photoResponse.getError();
                }
            }
        }
        else
        {
            error = addAuthorResponse.getError();
        }

        if(error == null)
        {
            ServiceResponse<Author> getAuthorResponse = authorService.get(newAuthor.getId());
            if(!getAuthorResponse.hasError())
            {
                author = getAuthorResponse.getData();
            }
            else
            {
                error = getAuthorResponse.getError();
            }
        }


        ServiceResponse<Author> response = new ServiceResponse<>(author);
        response.setError(error);

        AddAuthorResponseEvent event = new AddAuthorResponseEvent(response);
        EventBus.getDefault().post(event);
    }
}
