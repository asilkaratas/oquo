package co.lowprofile.ooquo.controller;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.event.AddCommentResponseEvent;
import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.model.NewComment;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.service.webapi.ICommentService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.IWebApi;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class AddCommentCommand extends Command
{
    private NewComment newComment;
    public AddCommentCommand(NewComment newComment)
    {
        this.newComment = newComment;
    }

    @Override
    public void execute()
    {
        IWebApi webApi = OoquoApplication.getWebApi();
        ICommentService commentService = webApi.getCommentService();
        ServiceResponse<NewComment> response = commentService.create(newComment);

        Comment comment = new Comment(newComment.getId(), newComment.getText());
        comment.setUser(OoquoApplication.getAppModel().getCurrentUser());

        ServiceResponse<Comment> commentResponse = new ServiceResponse<>(comment);
        commentResponse.setError(response.getError());

        AddCommentResponseEvent event = new AddCommentResponseEvent(newComment.getQuoteId(), commentResponse);
        EventBus.getDefault().post(event);
    }
}
