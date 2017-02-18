package co.lowprofile.ooquo.event;

/**
 * Created by asilkaratas on 12/10/15.
 */
public class GenericEvent<T>
{
    private String type;
    private  T data;
    public GenericEvent(String type, T data)
    {
        this.type = type;
        this.data = data;
    }

    public String getType()
    {
        return type;
    }
    public T getData()
    {
        return data;
    }

}
