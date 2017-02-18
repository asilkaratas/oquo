package co.lowprofile.ooquo.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by asilkaratas on 1/25/16.
 */
public class FileService
{
    private static FileService instance = null;

    public static FileService getInstance()
    {
        if(instance == null)
        {
            instance = new FileService();
        }
        return instance;
    }

    private FileService()
    {

    }

    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }

    public File saveTempPhoto(Bitmap bitmap, String fileName, File directory)
    {
        File file = null;
        FileOutputStream out = null;
        try
        {
            file = File.createTempFile(fileName, null, directory);
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        catch(IOException e)
        {
            return null;
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                return null;
            }
        }

        return file;
    }
}
