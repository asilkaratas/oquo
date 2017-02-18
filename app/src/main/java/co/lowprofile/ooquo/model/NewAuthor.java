package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class NewAuthor
{
    @SerializedName("Id")
    private int id;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

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
}
