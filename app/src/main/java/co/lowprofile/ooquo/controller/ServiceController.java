package co.lowprofile.ooquo.controller;


/**
 * Created by asilkaratas on 12/6/15.
 */
public class ServiceController
{
    private static final String TAG = "ServiceController";

    public ServiceController()
    {
    }


    /*
    public void onEventBackgroundThread(GenericEvent event)
    {
        switch (event.getType())
        {
            case EventType.LOGIN:
                onLogin(event);
                break;

            case EventType.SIGN_UP:
                onSignUp(event);
                break;

            case EventType.UPDATE_PROFILE:
                onUpdatePofile(event);
                break;

            case EventType.GET_USER_PROFILE:
                onGetUserProfile(event);
                break;

            case EventType.GET_FEED:
                onGetFeed(event);
                break;

            case EventType.ADD_AUTHOR:
                onAddAuthor(event);
                break;

            case EventType.GET_AUTHOR_PROFILE:
                onGetAuthorProfile(event);
                break;

            case EventType.SEARCH_AUTHOR:
                onSearchAuthor(event);
                break;

            case EventType.ADD_BOOK:
                onAddBook(event);
                break;

            case EventType.SEARCH_BOOK:
                onSearchBook(event);
                break;

            case EventType.GET_BOOK_PROFILE:
                onGetBookProfile(event);
                break;

            case EventType.ADD_QUOTE:
                onAddQuote(event);
                break;

            case EventType.LIKE:
                onLike(event);
                break;

            case EventType.DISLIKE:
                onDislike(event);
                break;

            case EventType.ADD_COMMENT:
                onAddComment(event);
                break;

            case EventType.GET_COMMENTS:
                onGetComments(event);
                break;

            case EventType.GET_USERS:
                onGetUsers(event);
                break;

            case EventType.FOLLOW:
                onFollow(event);
                break;

            case EventType.UNFOLLOW:
                onUnfollow(event);
                break;

            case EventType.SEARCH_QUOTE:
                onSearchQuote(event);
                break;

            case EventType.SEARCH_QUOTE_BOOK:
                onSearchQuoteBook(event);
                break;

            case EventType.GET_QUOTE_DETAIL:
                onGetQuoteDetail(event);
                break;
        }
    }

    private void onLogin(GenericEvent<User> event)
    {
        GenericResponse<User> response = service.login(event.getData());

        if(!response.hasError())
        {
            OoquoApplication.getAppModel().setCurrentUser(response.getData());
        }

        GenericEvent<GenericResponse<User>> responseEvent = new GenericEvent<>(EventType.LOGIN_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onSignUp(GenericEvent<User> event)
    {
        GenericResponse<User> response = service.signUp(event.getData());

        if(!response.hasError())
        {
            OoquoApplication.getAppModel().setCurrentUser(response.getData());
        }

        GenericEvent<GenericResponse<User>> responseEvent = new GenericEvent<>(EventType.SIGN_UP_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onUpdatePofile(GenericEvent<User> event)
    {
        GenericResponse<User> response = service.updateProfile(event.getData());

        if(!response.hasError())
        {
            OoquoApplication.getAppModel().setCurrentUser(response.getData());
        }

        GenericEvent<GenericResponse<User>> responseEvent = new GenericEvent<>(EventType.UPDATE_PROFILE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onGetUserProfile(GenericEvent<Integer> event)
    {
        GenericResponse<UserProfile> response = service.getUserProfile(event.getData());
        GenericEvent<GenericResponse<UserProfile>> responseEvent = new GenericEvent<>(EventType.GET_USER_PROFILE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onGetFeed(GenericEvent event)
    {
        GenericResponse<List<Feed>> response = service.getFeed();
        GenericEvent<GenericResponse<List<Feed>>> responseEvent = new GenericEvent<>(EventType.GET_FEED_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onAddAuthor(GenericEvent<Author> event)
    {
        GenericResponse<Author> response = service.addAuthor(event.getData());
        GenericEvent<GenericResponse<Author>> responseEvent = new GenericEvent<>(EventType.ADD_BOOK_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onGetAuthorProfile(GenericEvent<Integer> event)
    {
        GenericResponse<AuthorProfile> response = service.getAuthorProfile(event.getData());
        GenericEvent<GenericResponse<AuthorProfile>> responseEvent = new GenericEvent<>(EventType.GET_AUTHOR_PROFILE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onSearchAuthor(GenericEvent<String> event)
    {
        GenericResponse<List<Author>> response = service.searchAuthor(event.getData());
        GenericEvent<GenericResponse<List<Author>>> responseEvent = new GenericEvent<>(EventType.SEARCH_AUTHOR_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onAddBook(GenericEvent<Book> event)
    {
        GenericResponse<Book> response = service.addBook(event.getData());
        GenericEvent<GenericResponse<Book>> responseEvent = new GenericEvent<>(EventType.ADD_BOOK_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onSearchBook(GenericEvent<String> event)
    {
        GenericResponse<List<Book>> response = service.searchBook(event.getData());
        GenericEvent<GenericResponse<List<Book>>> responseEvent = new GenericEvent<>(EventType.SEARCH_BOOK_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onGetBookProfile(GenericEvent<Integer> event)
    {
        GenericResponse<BookProfile> response = service.getBookProfile(event.getData());
        GenericEvent<GenericResponse<BookProfile>> responseEvent = new GenericEvent<>(EventType.GET_BOOK_PROFILE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onAddQuote(GenericEvent<Quote> event)
    {
        GenericResponse<Quote> response = service.addQuote(event.getData());
        GenericEvent<GenericResponse<Quote>> responseEvent = new GenericEvent<>(EventType.ADD_QUOTE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onLike(GenericEvent<Like> event)
    {
        GenericResponse<Like> response = service.like(event.getData());
        GenericEvent<GenericResponse<Like>> responseEvent = new GenericEvent<>(EventType.LIKE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onDislike(GenericEvent<Like> event)
    {
        GenericResponse<Like> response = service.dislike(event.getData());
        GenericEvent<GenericResponse<Like>> responseEvent = new GenericEvent<>(EventType.DISLIKE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onAddComment(GenericEvent<Comment> event)
    {
        GenericResponse<Comment> response = service.addComment(event.getData());
        GenericEvent<GenericResponse<Comment>> responseEvent = new GenericEvent<>(EventType.ADD_COMMENT_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onGetComments(GenericEvent<Integer> event)
    {
        GenericResponse<List<Comment>> response = service.getComments(event.getData());
        GenericEvent<GenericResponse<List<Comment>>> responseEvent = new GenericEvent<>(EventType.GET_COMMENTS_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onGetUsers(GenericEvent<GetUsers> event)
    {
        GetUsers getUsers = event.getData();
        GenericResponse<List<User>> response = null;

        switch (getUsers.getType())
        {
            case LIKES:
                response = service.getLikes(getUsers.getId());
                break;

            case FOLLOWING:
                response = service.getFollowing(getUsers.getId());
                break;

            case FOLLOWERS:
                response = service.getFollowers(getUsers.getId());
                break;
        }

        GenericEvent<GenericResponse<List<User>>> responseEvent = new GenericEvent<>(EventType.GET_USERS_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onFollow(GenericEvent<Follow> event)
    {
        GenericResponse<Follow> response = service.follow(event.getData());
        GenericEvent<GenericResponse<Follow>> responseEvent = new GenericEvent<>(EventType.FOLLOW_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onUnfollow(GenericEvent<Follow> event)
    {
        GenericResponse<Follow> response = service.unfollow(event.getData());
        GenericEvent<GenericResponse<Follow>> responseEvent = new GenericEvent<>(EventType.UNFOLLOW_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onSearchQuote(GenericEvent<SearchQuote> event)
    {
        SearchQuote searchQuote = event.getData();
        GenericResponse<List<Quote>> response = null;

        switch (searchQuote.getType())
        {
            case USER:
                response = service.searchUserQuotes(searchQuote.getId(), searchQuote.getKey());
                break;

            case BOOK:
                response = service.searchBookQuotes(searchQuote.getId(), searchQuote.getKey());
                break;

            case AUTHOR:
                response = service.searchAuthorQuotes(searchQuote.getId(), searchQuote.getKey());
                break;
        }

        GenericEvent<GenericResponse<List<Quote>>> responseEvent = new GenericEvent<>(EventType.SEARCH_QUOTE_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onSearchQuoteBook(GenericEvent<SearchBook> event)
    {
        SearchBook searchBook = event.getData();
        GenericResponse<List<Book>> response = null;

        switch (searchBook.getType())
        {
            case USER:
                response = service.searchUserBooks(searchBook.getId(), searchBook.getKey());
                break;

            case AUTHOR:
                response = service.searchAuthorBooks(searchBook.getId(), searchBook.getKey());
                break;
        }

        GenericEvent<GenericResponse<List<Book>>> responseEvent = new GenericEvent<>(EventType.SEARCH_QUOTE_BOOK_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }

    private void onGetQuoteDetail(GenericEvent<Integer> event)
    {
        GenericResponse<Feed> response = service.getQuoteDetail(event.getData());
        GenericEvent<GenericResponse<Feed>> responseEvent = new GenericEvent<>(EventType.GET_QUOTE_DETAIL_RESPONSE, response);
        EventBus.getDefault().post(responseEvent);
    }
*/


}
