package co.lowprofile.ooquo.service.webapi;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface IBookService
{
    ServiceResponse<NewBook> create(NewBook newBook);

    ServiceResponse<Book> get(int bookId);

    ServiceResponse<BookProfile> getBookProfile(int bookId);

    ServiceResponse<List<Book>> search(SearchParams searchParams);
}
