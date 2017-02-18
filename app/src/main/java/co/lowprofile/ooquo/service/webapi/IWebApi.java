package co.lowprofile.ooquo.service.webapi;

/**
 * Created by asilkaratas on 1/16/16.
 */
public interface IWebApi
{
    IAccountService getAccountService();
    IUserService getUserService();
    IPhotoService getPhotoService();

    IAuthorService getAuthorService();
    IBookService getBookService();
    IQuoteService getQuoteService();
    ICommentService getCommentService();
    ILikeService getLikeService();
    IFollowService getFollowService();
}
