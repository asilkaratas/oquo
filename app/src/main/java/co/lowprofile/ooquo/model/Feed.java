package co.lowprofile.ooquo.model;

import java.util.List;

/**
 * Created by asilkaratas on 12/8/15.
 */
public class Feed
{
    private Quote quote;
    private int likeCount;
    private int commentCount;
    private List<Comment> lastComments;
    private boolean isLiked;


    public Quote getQuote()
    {
        return quote;
    }

    public void setQuote(Quote quote)
    {
        this.quote = quote;
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

    public List<Comment> getLastComments()
    {
        return lastComments;
    }

    public void setLastComments(List<Comment> lastComments)
    {
        this.lastComments = lastComments;
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
