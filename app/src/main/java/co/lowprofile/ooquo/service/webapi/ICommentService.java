package co.lowprofile.ooquo.service.webapi;

import java.util.List;

import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.model.NewComment;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface ICommentService
{
    ServiceResponse<NewComment> create(NewComment newComment);
    ServiceResponse<List<Comment>> getLastComments(int quoteId);
    ServiceResponse<Integer> getCommentCount(int quoteId);
}
