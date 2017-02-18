package co.lowprofile.ooquo.view.ui;

import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Comment;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;

/**
 * Created by asilkaratas on 12/7/15.
 */
public class CommentListItem extends BaseListItem
{
    private Comment comment;
    public CommentListItem(Comment comment)
    {
        this.comment = comment;
    }

    public Comment getComment()
    {
        return comment;
    }
}
