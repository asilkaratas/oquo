package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class AuthorListItemView extends FrameLayout implements ListItemView
{
    private static final String TAG = "AuthorListItemView";

    public AuthorListItemView(Context context)
    {
        this(context, null);
    }

    public AuthorListItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public AuthorListItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.author_list_item_view, this);
    }

    @Override
    public void setItem(BaseListItem item, int position, OnClickListener onClickListener)
    {
        AuthorListItem authorListItem = (AuthorListItem)item;

        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setText(authorListItem.getAuthor().getFullName());

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(imageView);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(authorListItem.getAuthor().getPhoto())
                .into(imageView);

        Log.d(TAG, authorListItem.getAuthor().getPhoto());

        View view = findViewById(R.id.authorListItem);
        view.setTag(position);
        view.setOnClickListener(onClickListener);
        view.setSelected(authorListItem.isSelected());
    }
}
