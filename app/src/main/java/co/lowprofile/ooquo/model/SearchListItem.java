package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 10/10/15.
 */
public class SearchListItem
{
    private String title;
    private int icon;
    private Object searchResultObject;

    public SearchListItem(String title, int icon, Object searchResultObject)
    {
        this.setTitle(title);
        this.setIcon(icon);
        this.setSearchResultObject(searchResultObject);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public Object getSearchResultObject()
    {
        return searchResultObject;
    }

    public void setSearchResultObject(Object searchResultObject)
    {
        this.searchResultObject = searchResultObject;
    }

    @Override
    public String toString()
    {
        return getTitle();
    }
}
