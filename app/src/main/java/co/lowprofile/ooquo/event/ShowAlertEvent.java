package co.lowprofile.ooquo.event;

/**
 * Created by asilkaratas on 12/8/15.
 */
public class ShowAlertEvent
{
    private String message;

    public ShowAlertEvent(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
