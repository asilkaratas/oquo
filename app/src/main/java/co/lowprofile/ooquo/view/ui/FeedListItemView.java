package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.AddCommentCommand;
import co.lowprofile.ooquo.controller.DislikeCommand;
import co.lowprofile.ooquo.controller.GetCommentCountCommand;
import co.lowprofile.ooquo.controller.GetLastCommentsCommand;
import co.lowprofile.ooquo.controller.GetLikeCountCommand;
import co.lowprofile.ooquo.controller.LikeCommand;
import co.lowprofile.ooquo.event.AddCommentResponseEvent;
import co.lowprofile.ooquo.event.DislikeResponseEvent;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.GetCommentCountResponseEvent;
import co.lowprofile.ooquo.event.GetLastCommentsResponseEvent;
import co.lowprofile.ooquo.event.GetLikeCountResponseEvent;
import co.lowprofile.ooquo.event.LikeResponseEvent;
import co.lowprofile.ooquo.event.ShowAlertEvent;
import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.model.Feed;
import co.lowprofile.ooquo.model.Like;
import co.lowprofile.ooquo.model.NewComment;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class FeedListItemView extends FrameLayout implements ListItemView
{
    public FeedListItemView(Context context)
    {
        this(context, null);
    }

    private Quote quote = null;
    private ImageView quoteImage;
    private ImageView profilePhoto;

    public FeedListItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public FeedListItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.feed_list_item_view, this);
    }

    @Override
    public void setItem(BaseListItem item, int position, OnClickListener onClickListener)
    {
        FeedListItem feedListItem = (FeedListItem)item;
        quote = feedListItem.getQuote();


        ImageView profilePhoto = (ImageView)findViewById(R.id.profilePhoto);
        profilePhoto.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(profilePhoto);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(quote.getUser().getProfilePhoto())
                .into(profilePhoto);


        ImageView quoteImage = (ImageView)findViewById(R.id.quoteImage);
        quoteImage.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(quoteImage);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(quote.getPhoto())
                .into(quoteImage);


        Button userNameButton = (Button)findViewById(R.id.userNameButton);
        userNameButton.setText(quote.getUser().getFullName());

        Button bookAuthorNameButton = (Button)findViewById(R.id.bookAuthorNameButton);
        bookAuthorNameButton.setText(quote.getBook().getTitle());

        Button likesButton = (Button)findViewById(R.id.likesButton);

        Button commentsButton = (Button)findViewById(R.id.commentsButton);

        final EditText commentInput = (EditText)findViewById(R.id.commentInput);

        updateCommentCount();
        updateComments();
        updateLikeCount();


        ImageButton likeButton = (ImageButton)findViewById(R.id.likeButton);
        likeButton.setSelected(quote.isLiked());

        ImageButton moreButton = (ImageButton)findViewById(R.id.moreButton);


        profilePhoto.setOnClickListener(onClickListener);
        profilePhoto.setTag(position);

        userNameButton.setOnClickListener(onClickListener);
        userNameButton.setTag(position);

        bookAuthorNameButton.setOnClickListener(onClickListener);
        bookAuthorNameButton.setTag(position);

        likesButton.setOnClickListener(onClickListener);
        likesButton.setTag(position);

        commentsButton.setOnClickListener(onClickListener);
        commentsButton.setTag(position);

        //likeButton.setOnClickListener(onClickListener);
        //likeButton.setTag(position);

        likeButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(quote.isLiked())
                {
                    new DislikeCommand(quote).executeInBackground();
                }
                else
                {
                    new LikeCommand(quote).executeInBackground();
                }
            }
        });


        moreButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(commentInput.getText().toString().length() < 1)
                {
                    EventBus.getDefault().post(new ShowAlertEvent("Comment can not be empty."));
                }
                else
                {
                    NewComment newComment = new NewComment();
                    newComment.setText(commentInput.getText().toString());
                    newComment.setQuoteId(quote.getId());

                    new AddCommentCommand(newComment).executeInBackground();

                    commentInput.setText("");
                }
            }
        });


        /*
        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setText(bookListItem.getBook().getTitle());

        TextView authorText = (TextView)findViewById(R.id.authorText);
        authorText.setText(bookListItem.getBook().getAuthor().getFullName());

        View view = findViewById(R.id.bookListItem);
        view.setTag(position);
        view.setOnClickListener(onClickListener);
        view.setSelected(bookListItem.isSelected());
        */
    }

    private void updateLikeCount()
    {
        Button likesButton = (Button)findViewById(R.id.likesButton);
        likesButton.setText(quote.getLikeCount() + " likes");
    }

    private void updateCommentCount()
    {
        Button commentsButton = (Button)findViewById(R.id.commentsButton);
        commentsButton.setText(quote.getCommentCount() + " comments");
    }

    private void updateComments()
    {
        LinearLayout commentsContainer = (LinearLayout)findViewById(R.id.commentsContainer);
        commentsContainer.removeAllViews();

        List<Comment> lastComments = quote.getComments();
        Collections.reverse(lastComments);
        for(Comment comment : lastComments)
        {
            CommentListItem listItem = new CommentListItem(comment);
            CommentListItemView listItemView = new CommentListItemView(getContext());
            listItemView.setItem(listItem, 0, null);

            //TextView textView = new TextView(getContext());
            //textView.setText(comment.getText());
            commentsContainer.addView(listItemView);
        }
    }

    public void onEventMainThread(LikeResponseEvent event)
    {
        int quoteId = event.getQuoteId();
        if(quote.getId() == quoteId)
        {
            ImageButton likeButton = (ImageButton)findViewById(R.id.likeButton);
            likeButton.setSelected(quote.isLiked());

            new GetLikeCountCommand(quote).executeInBackground();
        }
    }

    public void onEventMainThread(DislikeResponseEvent event)
    {
        int quoteId = event.getQuoteId();
        if(quote.getId() == quoteId)
        {
            ImageButton likeButton = (ImageButton)findViewById(R.id.likeButton);
            likeButton.setSelected(quote.isLiked());

            new GetLikeCountCommand(quote).executeInBackground();
        }
    }

    public void onEventMainThread(GetLikeCountResponseEvent event)
    {
        if(event.getQuoteId() == quote.getId())
        {
            Button likesButton = (Button)findViewById(R.id.likesButton);
            likesButton.setText(quote.getLikeCount() + " likes");
        }
    }

    public void onEventMainThread(AddCommentResponseEvent event)
    {
        if(event.getQuoteId() == quote.getId())
        {
            new GetLastCommentsCommand(quote).executeInBackground();
            new GetCommentCountCommand(quote).executeInBackground();
        }
    }

    public void onEventMainThread(GetLastCommentsResponseEvent event)
    {
        if(event.getQuoteId() == quote.getId())
        {
            updateComments();
        }
    }

    public void onEventMainThread(GetCommentCountResponseEvent event)
    {
        if(event.getQuoteId() == quote.getId())
        {
            updateCommentCount();
        }
    }



    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
        quote = null;
    }
}
