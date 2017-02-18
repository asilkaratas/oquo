package co.lowprofile.ooquo.controller;

import android.graphics.Bitmap;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.AddBookResponseEvent;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.service.webapi.IBookService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddBookCommand extends Command
{
    private NewBook newBook;
    private Bitmap bitmap;
    public AddBookCommand(NewBook newBook, Bitmap bitmap)
    {
        this.newBook = newBook;
        this.bitmap = bitmap;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IBookService bookService = webApi.getBookService();
        IPhotoService photoService = webApi.getPhotoService();
        ServiceResponse<NewBook> addBookResponse = bookService.create(newBook);

        ServiceError error = null;
        Book book = null;
        if(!addBookResponse.hasError())
        {
            newBook = addBookResponse.getData();
            if(bitmap != null)
            {
                ServiceResponse<Boolean> photoResponse = photoService.uploadBookPhotoWithBitmap(newBook.getId(), bitmap);
                if(photoResponse.hasError())
                {
                    error = photoResponse.getError();
                }
            }
        }
        else
        {
            error = addBookResponse.getError();
        }

        if(error == null)
        {
            ServiceResponse<Book> getBookResponse = bookService.get(newBook.getId());
            if(!getBookResponse.hasError())
            {
                book = getBookResponse.getData();
            }
            else
            {
                error = getBookResponse.getError();
            }
        }


        ServiceResponse<Book> response = new ServiceResponse<>(book);
        response.setError(error);

        AddBookResponseEvent event = new AddBookResponseEvent(response);
        EventBus.getDefault().post(event);
    }
}
