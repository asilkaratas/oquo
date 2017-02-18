package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 12/13/15.
 */
public class UserProfile
{
    @SerializedName("Id")
    private int id;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("PhotoUrl")
    private String photoUrl;

    @SerializedName("FollowerCount")
    private int followerCount;

    @SerializedName("FollowingCount")
    private int followingCount;

    @SerializedName("IsFollowed")
    private boolean isFollowed;

    @SerializedName("IsSelf")
    private boolean isSelf;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }

    public int getFollowerCount()
    {
        return followerCount;
    }

    public void setFollowerCount(int followerCount)
    {
        this.followerCount = followerCount;
    }

    public int getFollowingCount()
    {
        return followingCount;
    }

    public void setFollowingCount(int followingCount)
    {
        this.followingCount = followingCount;
    }

    public boolean isFollowed()
    {
        return isFollowed;
    }

    public void setIsFollowed(boolean isFollowed)
    {
        this.isFollowed = isFollowed;
    }

    public boolean isSelf()
    {
        return isSelf;
    }

    public void setIsSelf(boolean isSelf)
    {
        this.isSelf = isSelf;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }
}
