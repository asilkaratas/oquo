package co.lowprofile.ooquo.service.webapi.retro;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.IBookService;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.AuthorProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.BookProxy;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/16/16.
 */
public class BookService implements IBookService
{
    private IRetroWebApi retroWebApi;
    public BookService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    @Override
    public ServiceResponse<NewBook> create(NewBook newBook)
    {
        BookProxy bookProxy = retroWebApi.getRetrofit().create(BookProxy.class);

        Call<NewBook> call = bookProxy.create(newBook);

        ServiceResponse<NewBook> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<Book> get(int bookId)
    {
        BookProxy bookProxy = retroWebApi.getRetrofit().create(BookProxy.class);

        Call<Book> call = bookProxy.get(bookId);

        ServiceResponse<Book> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<BookProfile> getBookProfile(int bookId)
    {
        BookProxy bookProxy = retroWebApi.getRetrofit().create(BookProxy.class);

        Call<BookProfile> call = bookProxy.getBookProfile(bookId);

        ServiceResponse<BookProfile> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<List<Book>> search(SearchParams searchParams)
    {
        BookProxy bookProxy = retroWebApi.getRetrofit().create(BookProxy.class);

        Call<List<Book>> call = bookProxy.search(searchParams);

        ServiceResponse<List<Book>> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }
}
