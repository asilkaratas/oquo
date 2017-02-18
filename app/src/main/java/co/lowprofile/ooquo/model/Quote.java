package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class Quote
{
    @SerializedName("Id")
    private int id;

    @SerializedName("Text")
    private String text;

    @SerializedName("BookId")
    private int bookId;

    @SerializedName("PhotoUrl")
    private String photo;

    @SerializedName("User")
    private User user;

    @SerializedName("Book")
    private Book book;

    @SerializedName("Comments")
    private List<Comment> comments;

    @SerializedName("LikeCount")
    private int likeCount;

    @SerializedName("CommentCount")
    private int commentCount;

    @SerializedName("IsLiked")
    private boolean isLiked;


    public Quote(int id, String text, String photo)
    {
        setId(id);
        setText(text);
        setPhoto(photo);

        comments = new ArrayList<Comment>();
    }

    public Quote(String info, String text, String photo)
    {
        this(0, text, photo);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }

    public List<Comment> getLastComments(int count)
    {
        List<Comment> lastComments = new ArrayList<Comment>();
        count = Math.min(count, comments.size());
        int start = comments.size() - count;
        for(int i = start; i < comments.size(); ++i)
        {
            lastComments.add(comments.get(i));
        }

        return lastComments;
    }

    public int getLikeCount()
    {
        return likeCount;
    }

    public void setLikeCount(int likeCount)
    {
        this.likeCount = likeCount;
    }

    public int getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(int commentCount)
    {
        this.commentCount = commentCount;
    }

    public boolean isLiked()
    {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked)
    {
        this.isLiked = isLiked;
    }
}
