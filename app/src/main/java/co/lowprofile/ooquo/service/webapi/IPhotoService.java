package co.lowprofile.ooquo.service.webapi;

import android.graphics.Bitmap;

import java.io.File;

import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/21/16.
 */
public interface IPhotoService
{
    ServiceResponse<Boolean> uploadUserPhoto(int userId, File file);
    ServiceResponse<Boolean> uploadUserPhotoWithBitmap(int userId, Bitmap bitmap);

    ServiceResponse<Boolean> uploadAuthorPhoto(int authorId, File file);
    ServiceResponse<Boolean> uploadAuthorPhotoWithBitmap(int authorId, Bitmap bitmap);

    ServiceResponse<Boolean> uploadBookPhoto(int bookId, File file);
    ServiceResponse<Boolean> uploadBookPhotoWithBitmap(int bookId, Bitmap bitmap);

    ServiceResponse<Boolean> uploadQuotePhoto(int quoteId, File file);
    ServiceResponse<Boolean> uploadQuotePhotoWithBitmap(int quoteId, Bitmap bitmap);
}
