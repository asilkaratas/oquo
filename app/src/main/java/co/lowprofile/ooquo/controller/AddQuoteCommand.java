package co.lowprofile.ooquo.controller;

import android.graphics.Bitmap;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.AddBookResponseEvent;
import co.lowprofile.ooquo.event.AddQuoteResponseEvent;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.IBookService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddQuoteCommand extends Command
{
    private NewQuote newQuote;
    private Bitmap bitmap;
    public AddQuoteCommand(NewQuote newQuote, Bitmap bitmap)
    {
        this.newQuote = newQuote;
        this.bitmap = bitmap;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        IQuoteService quoteService = webApi.getQuoteService();
        IPhotoService photoService = webApi.getPhotoService();
        ServiceResponse<NewQuote> addQuoteResponse = quoteService.create(newQuote);

        ServiceError error = null;
        Quote quote = null;
        if(!addQuoteResponse.hasError())
        {
            newQuote = addQuoteResponse.getData();
            if(bitmap != null)
            {
                ServiceResponse<Boolean> photoResponse = photoService.uploadQuotePhotoWithBitmap(newQuote.getId(), bitmap);
                if(photoResponse.hasError())
                {
                    error = photoResponse.getError();
                }
            }
        }
        else
        {
            error = addQuoteResponse.getError();
        }

        if(error == null)
        {
            ServiceResponse<Quote> getQuoteResponse = quoteService.get(newQuote.getId());
            if(!getQuoteResponse.hasError())
            {
                quote = getQuoteResponse.getData();
            }
            else
            {
                error = getQuoteResponse.getError();
            }
        }


        ServiceResponse<Quote> response = new ServiceResponse<>(quote);
        response.setError(error);

        AddQuoteResponseEvent event = new AddQuoteResponseEvent(response);
        EventBus.getDefault().post(event);
    }
}
