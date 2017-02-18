package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class Author
{
    @SerializedName("Id")
    private int id;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("PhotoUrl")
    private String photo;

    public Author(int id, String firstName, String lastName, String photo)
    {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoto(photo);
    }

    public Author(String firstName, String lastName, String photo)
    {
        this(0, firstName, lastName, photo);
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

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
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
