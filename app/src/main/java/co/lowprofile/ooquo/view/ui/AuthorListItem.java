package co.lowprofile.ooquo.view.ui;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;

/**
 * Created by asilkaratas on 12/7/15.
 */
public class AuthorListItem extends BaseListItem
{
    private Author author;
    public AuthorListItem(Author author)
    {
        this.author = author;
    }

    public Author getAuthor()
    {
        return author;
    }
}
