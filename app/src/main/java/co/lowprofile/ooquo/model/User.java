package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class User
{
    public static User getDummyUser()
    {
        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        user.setProfilePhoto("src/main/assets/images/adult_small_icon.png");

        return user;
    }

    @SerializedName("Id")
    private int id;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;


    @SerializedName("PhotoUrl")
    private String profilePhoto;



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

    public String getProfilePhoto()
    {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto)
    {
        this.profilePhoto = profilePhoto;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }
}
