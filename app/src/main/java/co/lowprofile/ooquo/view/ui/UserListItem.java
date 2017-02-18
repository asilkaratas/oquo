package co.lowprofile.ooquo.view.ui;

import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;

/**
 * Created by asilkaratas on 12/7/15.
 */
public class UserListItem extends BaseListItem
{
    private User user;
    public UserListItem(User user)
    {
        this.user = user;
    }

    public User getUser()
    {
        return user;
    }
}
