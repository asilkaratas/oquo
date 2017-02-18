package co.lowprofile.ooquo.service.webapi;

import java.util.List;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.NewAuthor;
import co.lowprofile.ooquo.model.SearchParams;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface IAuthorService
{
    ServiceResponse<NewAuthor> create(NewAuthor newAuthor);

    ServiceResponse<Author> get(int authorId);

    ServiceResponse<List<Author>> search(SearchParams searchParams);
}
