package co.lowprofile.ooquo.service.webapi.retro;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.service.FileService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.common.ServiceError;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.PhotoProxy;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/21/16.
 */
public class PhotoService implements IPhotoService
{
    private IRetroWebApi retroWebApi;
    public PhotoService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    private ServiceResponse<Boolean> uploadPhoto(Call<ResponseBody> call)
    {
        ServiceResponse<ResponseBody> response = retroWebApi.getCallExecuter().execute(call);

        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<>(!response.hasError());
        serviceResponse.setError(response.getError());
        return serviceResponse;
    }

    private RequestBody getFileRequestBody(File file)
    {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return requestBody;
    }

    private ServiceResponse<Boolean> createFileSavingErrorResponse()
    {
        ServiceError error = new ServiceError("Saving file error.");
        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<>(false);
        serviceResponse.setError(error);
        return serviceResponse;
    }

    private File saveTempPhoto(Bitmap bitmap, String fileName)
    {
        File tempDir = OoquoApplication.getInstance().getCacheDir();
        File file = FileService.getInstance().saveTempPhoto(bitmap, fileName, tempDir);
        return file;
    }

    @Override
    public ServiceResponse<Boolean> uploadUserPhoto(int userId, File file)
    {
        PhotoProxy photoProxy = retroWebApi.getRetrofit().create(PhotoProxy.class);
        Call<ResponseBody> call = photoProxy.uploadUserPhoto(userId, getFileRequestBody(file));
        return uploadPhoto(call);
    }

    @Override
    public ServiceResponse<Boolean> uploadUserPhotoWithBitmap(int userId, Bitmap bitmap)
    {
        File file = saveTempPhoto(bitmap, "temp_user_photo.jpg");

        if(file == null)
        {
            return createFileSavingErrorResponse();
        }

        return uploadUserPhoto(userId, file);
    }

    @Override
    public ServiceResponse<Boolean> uploadAuthorPhoto(int authorId, File file)
    {
        PhotoProxy photoProxy = retroWebApi.getRetrofit().create(PhotoProxy.class);
        Call<ResponseBody> call = photoProxy.uploadAuthorPhoto(authorId, getFileRequestBody(file));
        return uploadPhoto(call);
    }

    @Override
    public ServiceResponse<Boolean> uploadAuthorPhotoWithBitmap(int authorId, Bitmap bitmap)
    {
        File file = saveTempPhoto(bitmap, "temp_user_photo.jpg");

        if(file == null)
        {
            return createFileSavingErrorResponse();
        }

        return uploadAuthorPhoto(authorId, file);
    }

    @Override
    public ServiceResponse<Boolean> uploadBookPhoto(int bookId, File file)
    {
        PhotoProxy photoProxy = retroWebApi.getRetrofit().create(PhotoProxy.class);
        Call<ResponseBody> call = photoProxy.uploadBookPhoto(bookId, getFileRequestBody(file));
        return uploadPhoto(call);
    }

    @Override
    public ServiceResponse<Boolean> uploadBookPhotoWithBitmap(int bookId, Bitmap bitmap)
    {
        File file = saveTempPhoto(bitmap, "temp_book_photo.jpg");

        if(file == null)
        {
            return createFileSavingErrorResponse();
        }

        return uploadBookPhoto(bookId, file);
    }

    @Override
    public ServiceResponse<Boolean> uploadQuotePhoto(int quoteId, File file)
    {
        PhotoProxy photoProxy = retroWebApi.getRetrofit().create(PhotoProxy.class);
        Call<ResponseBody> call = photoProxy.uploadQuotePhoto(quoteId, getFileRequestBody(file));
        return uploadPhoto(call);
    }

    @Override
    public ServiceResponse<Boolean> uploadQuotePhotoWithBitmap(int quoteId, Bitmap bitmap)
    {
        File file = saveTempPhoto(bitmap, "temp_quote_photo.jpg");

        if(file == null)
        {
            return createFileSavingErrorResponse();
        }

        return uploadQuotePhoto(quoteId, file);
    }

}
