package co.lowprofile.ooquo.service.webapi.retro.common;

/**
 * Created by asilkaratas on 1/19/16.
 */
public class AccessToken
{
    private String accessToken;
    private String tokenType;

    public AccessToken(String tokenType, String accessToken)
    {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    public String getTokenType()
    {
        return tokenType;
    }

    public void setTokenType(String tokenType)
    {
        this.tokenType = tokenType;
    }
}
