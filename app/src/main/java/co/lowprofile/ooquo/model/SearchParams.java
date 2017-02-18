package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class SearchParams
{
    @SerializedName("Key")
    private String key;

    @SerializedName("MaxResults")
    private int maxResults;

    public SearchParams(String key, int maxResults)
    {
        this.key = key;
        this.maxResults = maxResults;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public int getMaxResults()
    {
        return maxResults;
    }

    public void setMaxResults(int maxResults)
    {
        this.maxResults = maxResults;
    }
}
