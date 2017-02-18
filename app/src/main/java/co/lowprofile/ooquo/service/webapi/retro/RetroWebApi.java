package co.lowprofile.ooquo.service.webapi.retro;

import co.lowprofile.ooquo.service.webapi.IAuthorService;
import co.lowprofile.ooquo.service.webapi.IAccountService;
import co.lowprofile.ooquo.service.webapi.IBookService;
import co.lowprofile.ooquo.service.webapi.ICommentService;
import co.lowprofile.ooquo.service.webapi.IFollowService;
import co.lowprofile.ooquo.service.webapi.ILikeService;
import co.lowprofile.ooquo.service.webapi.IPhotoService;
import co.lowprofile.ooquo.service.webapi.IQuoteService;
import co.lowprofile.ooquo.service.webapi.IUserService;
import co.lowprofile.ooquo.service.webapi.retro.common.AuthInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by asilkaratas on 1/12/16.
 */
public class RetroWebApi implements IRetroWebApi
{
    private IAccountService accountService;
    private IUserService userService;
    private IPhotoService photoService;
    private IAuthorService authorService;
    private IBookService bookService;
    private IQuoteService quoteService;
    private ICommentService commentService;
    private ILikeService likeService;
    private IFollowService followService;

    private AuthInterceptor authInterceptor;
    private Retrofit retrofit;
    private ICallExecuter callExecuter;

    public RetroWebApi(String apiBaseUrl)
    {
        accountService = new AccountService(this);
        userService = new UserService(this);
        photoService = new PhotoService(this);
        authorService = new AuthorService(this);
        bookService = new BookService(this);
        quoteService = new QuoteService(this);
        commentService = new CommentService(this);
        likeService = new LikeService(this);
        followService = new FollowService(this);

        Retrofit.Builder  builder = new Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        authInterceptor = new AuthInterceptor();
        OkHttpClient httpClient = ClientFactory.getOkClient(authInterceptor);

        retrofit = builder.client(httpClient).build();

        callExecuter = new CallExecuter();
    }

    public IAccountService getAccountService()
    {
        return accountService;
    }

    public IUserService getUserService()
    {
        return userService;
    }

    public IPhotoService getPhotoService()
    {
        return photoService;
    }

    public IAuthorService getAuthorService()
    {
        return authorService;
    }

    public IQuoteService getQuoteService()
    {
        return quoteService;
    }

    public ICommentService getCommentService()
    {
        return commentService;
    }

    public IBookService getBookService()
    {
        return bookService;
    }

    public ILikeService getLikeService() { return likeService; }

    public IFollowService getFollowService() { return followService; }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }

    public AuthInterceptor getAuthInterceptor()
    {
        return authInterceptor;
    }

    public boolean isLogined()
    {
        return authInterceptor.getAccessToken() != null && authInterceptor.getAccessToken().getAccessToken() != null;
    }

    public ICallExecuter getCallExecuter()
    {
        return callExecuter;
    }

}
