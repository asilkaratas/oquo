package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 12/14/15.
 */
public class AuthorProfile
{
    private Author author;
    private int quoteCount;
    private int bookCount;

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public int getQuoteCount()
    {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount)
    {
        this.quoteCount = quoteCount;
    }

    public int getBookCount()
    {
        return bookCount;
    }

    public void setBookCount(int bookCount)
    {
        this.bookCount = bookCount;
    }
}
