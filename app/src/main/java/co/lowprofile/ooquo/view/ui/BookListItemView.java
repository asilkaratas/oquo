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
public class BookListItemView extends FrameLayout implements ListItemView
{
    public BookListItemView(Context context)
    {
        this(context, null);
    }

    public BookListItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public BookListItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.book_list_item_view, this);
    }

    @Override
    public void setItem(BaseListItem item, int position, OnClickListener onClickListener)
    {
        BookListItem bookListItem = (BookListItem)item;

        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setText(bookListItem.getBook().getTitle());

        TextView authorText = (TextView)findViewById(R.id.authorText);
        authorText.setText(bookListItem.getBook().getAuthor().getFullName());

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(imageView);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(bookListItem.getBook().getCoverPhoto())
                .into(imageView);


        View view = findViewById(R.id.bookListItem);
        view.setTag(position);
        view.setOnClickListener(onClickListener);
        view.setSelected(bookListItem.isSelected());
    }
}
