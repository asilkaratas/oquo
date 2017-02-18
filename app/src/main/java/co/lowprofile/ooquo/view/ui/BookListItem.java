package co.lowprofile.ooquo.view.ui;

import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;

/**
 * Created by asilkaratas on 12/7/15.
 */
public class BookListItem extends BaseListItem
{
    private Book book;
    public BookListItem(Book book)
    {
        this.book = book;
    }

    public Book getBook()
    {
        return book;
    }
}
