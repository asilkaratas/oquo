package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class UserListItemView extends FrameLayout implements ListItemView
{
    public UserListItemView(Context context)
    {
        this(context, null);
    }

    public UserListItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public UserListItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.user_list_item_view, this);
    }

    @Override
    public void setItem(BaseListItem item, int position, OnClickListener onClickListener)
    {
        UserListItem userListItem = (UserListItem)item;

        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setText(userListItem.getUser().getFullName());

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(imageView);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(userListItem.getUser().getProfilePhoto())
                .into(imageView);

        View view = findViewById(R.id.userListItem);
        view.setTag(position);
        view.setOnClickListener(onClickListener);
    }
}
