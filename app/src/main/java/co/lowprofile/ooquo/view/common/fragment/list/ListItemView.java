package co.lowprofile.ooquo.view.common.fragment.list;

import android.view.View;

/**
 * Created by asilkaratas on 10/20/15.
 */
public interface ListItemView
{
    void setItem(BaseListItem item, int position, View.OnClickListener onClickListener);
}
