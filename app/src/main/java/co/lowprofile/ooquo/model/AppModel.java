package co.lowprofile.ooquo.model;

import android.graphics.Bitmap;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class AppModel
{
    private Bitmap quoteBitmap = null;
    private Bitmap bookBitmap = null;
    private Bitmap authorBitmap = null;

    private Bitmap bitmap = null;
    private boolean takePhoto = false;
    private User currentUser = null;
    private Author selectedAuthor = null;
    private Book selectedBook = null;

    private boolean refreshFeed;

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }


    public boolean isTakePhoto()
    {
        return takePhoto;
    }

    public void setTakePhoto(boolean takePhoto)
    {
        this.takePhoto = takePhoto;
    }


    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

    public Author getSelectedAuthor()
    {
        return selectedAuthor;
    }

    public void setSelectedAuthor(Author selectedAuthor)
    {
        this.selectedAuthor = selectedAuthor;
    }

    public Book getSelectedBook()
    {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook)
    {
        this.selectedBook = selectedBook;
    }

    public boolean isCurrentUser(User user)
    {
        return user.getId() == currentUser.getId();
    }


    public boolean isRefreshFeed()
    {
        return refreshFeed;
    }

    public void setRefreshFeed(boolean refreshFeed)
    {
        this.refreshFeed = refreshFeed;
    }

    public Bitmap getQuoteBitmap()
    {
        return quoteBitmap;
    }

    public void setQuoteBitmap(Bitmap quoteBitmap)
    {
        this.quoteBitmap = quoteBitmap;
    }

    public Bitmap getBookBitmap()
    {
        return bookBitmap;
    }

    public void setBookBitmap(Bitmap bookBitmap)
    {
        this.bookBitmap = bookBitmap;
    }

    public Bitmap getAuthorBitmap()
    {
        return authorBitmap;
    }

    public void setAuthorBitmap(Bitmap authorBitmap)
    {
        this.authorBitmap = authorBitmap;
    }
}
