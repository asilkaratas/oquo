package co.lowprofile.ooquo.service;

import android.content.Context;
import android.net.Uri;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class CustomImageDownloader extends BaseImageDownloader
{
    public CustomImageDownloader(Context context)
    {
        super(context);
    }

    public CustomImageDownloader(Context context, int connectTimeout, int readTimeout)
    {
        super(context, connectTimeout, readTimeout);
    }

     protected HttpURLConnection createConnection(String url, Object extra) throws IOException
    {
        String encodedUrl = Uri.encode(url, ALLOWED_URI_CHARS);
        HttpURLConnection conn = (HttpURLConnection) new URL(encodedUrl).openConnection();
        conn.setConnectTimeout(connectTimeout);
        conn.setReadTimeout(readTimeout);

        return conn;
    }
}
