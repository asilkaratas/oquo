package co.lowprofile.ooquo.service.webapi.retro;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.AuthorProfile;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.service.webapi.retro.common.AuthorProxy;
import co.lowprofile.ooquo.service.webapi.retro.common.LoginResponse;
import retrofit2.Call;

/**
 * Created by asilkaratas on 1/16/16.
 */
public class AuthorService implements IAuthorService
{
    private IRetroWebApi retroWebApi;
    public AuthorService(IRetroWebApi retroWebApi)
    {
        this.retroWebApi = retroWebApi;
    }

    @Override
    public ServiceResponse<NewAuthor> create(NewAuthor newAuthor)
    {
        AuthorProxy authorProxy = retroWebApi.getRetrofit().create(AuthorProxy.class);

        Call<NewAuthor> call = authorProxy.create(newAuthor);

        ServiceResponse<NewAuthor> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<Author> get(int authorId)
    {
        AuthorProxy authorProxy = retroWebApi.getRetrofit().create(AuthorProxy.class);

        Call<Author> call = authorProxy.get(authorId);

        ServiceResponse<Author> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }

    @Override
    public ServiceResponse<List<Author>> search(SearchParams searchParams)
    {
        AuthorProxy authorProxy = retroWebApi.getRetrofit().create(AuthorProxy.class);

        Call<List<Author>> call = authorProxy.search(searchParams);

        ServiceResponse<List<Author>> response = retroWebApi.getCallExecuter().execute(call);

        return response;
    }
}
