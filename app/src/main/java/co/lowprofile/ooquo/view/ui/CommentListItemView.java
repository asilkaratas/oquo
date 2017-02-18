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
import co.lowprofile.ooquo.event.ShowFragmentEvent;
import co.lowprofile.ooquo.view.UserProfileFragment;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class CommentListItemView extends FrameLayout implements ListItemView
{
    public CommentListItemView(Context context)
    {
        this(context, null);
    }

    public CommentListItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CommentListItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.comment_list_item_view, this);
    }

    @Override
    public void setItem(BaseListItem item, int position, OnClickListener onClickListener)
    {
        final CommentListItem commentListItem = (CommentListItem)item;

        TextView commentText = (TextView)findViewById(R.id.commentText);
        commentText.setText(commentListItem.getComment().getText());

        TextView userNameText = (TextView)findViewById(R.id.userNameText);
        userNameText.setText(commentListItem.getComment().getUser().getFirstName());


        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(imageView);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(commentListItem.getComment().getUser().getProfilePhoto())
                .into(imageView);

        imageView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //OoquoApplication.getInstance().
                ShowFragmentEvent event = new ShowFragmentEvent(UserProfileFragment.class,
                        UserProfileFragment.createBundle(commentListItem.getComment().getUser().getId()));
                EventBus.getDefault().post(event);
            }
        });


        //View view = findViewById(R.id.bookListItem);
        //view.setTag(position);
        //view.setOnClickListener(onClickListener);
    }
}
